package database;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;



public class DataLoader {


    // Все данные из базы
    private  ObservableList<Film> filmInfoList = FXCollections.observableArrayList();
    private  ObservableList<Session> sessionList = FXCollections.observableArrayList();
    private  ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    private  ObservableList<Order> orderList = FXCollections.observableArrayList();
    private  ObservableList<Integer> orderNumsList = FXCollections.observableArrayList();
    private  ArrayList<Theater> theatersList = new ArrayList<>();
    private  ArrayList<Image> postersList = new ArrayList<>();
    private  ObservableList<String> filmNames = FXCollections.observableArrayList();
    private  ObservableList<Time> filmtimes = FXCollections.observableArrayList();

    // Геттеры списков
    public  ObservableList<Ticket> getTicketList() {
        return ticketList;
    }
    public  ArrayList<Image> getPostersList() {
        return postersList;
    }
    public  ObservableList<Film> getFilmInfoList() {
        return filmInfoList;
    }
    public  ObservableList<Session> getSessionList() {
        return sessionList;
    }
    public  ArrayList<Theater> getTheatersList() {
        return theatersList;
    }
    public  ObservableList<Order> getOrderList() {
        return orderList;
    }
    public  ObservableList<Integer> getOrderNumsList() {
        return orderNumsList;
    }
    public  ObservableList<String> getFilmNames() {
        return filmNames;
    }

    /**
     * Загрузка списка фильмов
     * @throws SQLException
     */
    public  void loadFilmInfo() throws SQLException {

        filmInfoList.clear();
        if (DBConnector.isConnected()) {

            ResultSet films = null;
            int i = 1;
            films = DBConnector.sendRequest(Requests.GET_ALL_FILMS);
            InputStream blobStream = null;

            while (films.next()) {
                    blobStream = films.getBlob(5).getBinaryStream();
                    filmInfoList.add(new Film(i, films.getString(1), films.getString(4), films.getString(6), films.getString(3), films.getInt(2), new Image(blobStream)));
                    i++;
            }
        }
    }

    /**
     * Загрузка расписания
     * @throws SQLException
     */
    public  void loadSchedule() throws SQLException {

        sessionList.clear();
        if (DBConnector.isConnected()) {

            ResultSet sessions = null;
            sessions = DBConnector.sendRequest(Requests.GET_SCHEDULE);

            while (sessions.next())
                sessionList.add(new Session(sessions.getString(2), sessions.getDate(4), sessions.getTime("SessionTime"), sessions.getInt(3), sessions.getInt(1), sessions.getInt("OrdCost"), sessions.getInt("VipCost")));

        }

    }

    /**
     * Загрузка постеров
     * @throws SQLException
     */
    public  void loadPosters() throws SQLException {

        postersList.clear();
        if (DBConnector.isConnected()) {
            ResultSet posters = DBConnector.sendRequest(Requests.GET_POSTERS);

            InputStream blobStream = null;
            Blob blob;

            while (posters.next()) {

                blob = posters.getBlob(1);
                blobStream = blob.getBinaryStream();
                postersList.add(new Image(blobStream));

            }
           if (blobStream != null) {
               try {
                   blobStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }

    }

    /**
     * Загрузка списка залов
     * @throws SQLException
     */
    public  void loadTheaters() throws SQLException {

        theatersList.clear();
        if (DBConnector.isConnected()) {

            ResultSet theaters = null;
            theaters = DBConnector.sendRequest(Requests.GET_THEATERS);

            while (theaters.next())
                theatersList.add(new Theater(theaters.getInt(1), theaters.getInt(2), theaters.getInt(3), theaters.getInt(4)));

        }


    }

    /**
     * Загрузка списка билетов
     * @throws SQLException
     */
    public  void loadTickets() throws SQLException {

        ticketList.clear();
        if (DBConnector.isConnected()) {

            ResultSet tickets = null;
            tickets = DBConnector.sendRequest(Requests.GET_TICKETS);

            while (tickets.next())
                ticketList.add(new Ticket(tickets.getInt("BookID"), tickets.getInt("idSessions"), tickets.getInt("idTickets"), tickets.getString("TicketType"), tickets.getInt("RowNumber"), tickets.getInt("SeatNumber")));

        }

    }

    /**
     * Загрузка списка заказов
     */
    public  void loadOrders() throws SQLException {

        orderList.clear();
        if (DBConnector.isConnected()) {

            ResultSet orders = null;
            orders = DBConnector.sendRequest(Requests.GET_ORDERS);

            while (orders.next()) {
                orderList.add(new Order(orders.getInt("idOrders"), orders.getDate("OrderDateTime"), orders.getInt("TotalCost")));
                orderNumsList.add(orders.getInt("idOrders"));
            }
        }

    }

    /**
     * Загрузка сеансов за определенную дату
     * @param date
     * @return
     * @throws SQLException
     */
    public static ObservableList<Session> getSessionsByDate(Date date) throws SQLException {

        ObservableList<Session> sessions = FXCollections.observableArrayList();
        PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.GET_SESSIONS_BY_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = new Date(calendar.getTime().getTime());
        statement.setString(1, (new java.sql.Date(date.getTime())).toString());
        ResultSet resultSet = statement.executeQuery();
        System.out.println(new java.sql.Date(date.getTime()));

        while (resultSet.next())
            sessions.add(new Session(resultSet.getString(2), resultSet.getDate(4), resultSet.getTime("SessionTime"), resultSet.getInt(3), resultSet.getInt(1), resultSet.getInt("OrdCost"), resultSet.getInt("VipCost")));

        return sessions;

    }

    /**
     * Загрузка названий фильмов
     * @return
     * @throws SQLException
     */
    public  ObservableList<String> loadFilmNames() throws SQLException {

        filmNames.clear();

        if (DBConnector.isConnected()) {

            ResultSet names = DBConnector.sendRequest(Requests.GET_FILM_NAMES);
            while (names.next())
                filmNames.add(names.getString(1));

        }

        return filmNames;
    }

    /**
     * Получение времени сеансов конретного фильма в конкретный день
     * @param filmname
     * @param date
     * @return
     * @throws SQLException
     */
    public  ObservableList<Time> getFilmtimesByDate(String filmname, java.sql.Date date) throws SQLException {

        filmtimes.clear();

        if (DBConnector.isConnected()) {

            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.GET_FILMTIMES_BY_DATE);
            statement.setDate(1, date);
            statement.setString(2,  filmname);
            ResultSet times = statement.executeQuery();
            while (times.next())
                filmtimes.add(times.getTime(1));
        }

        return filmtimes;
    }

    /**
     * Получение сеанса по дате, времени и названию фильма
     * @param filmname
     * @param date
     * @param time
     * @return
     */
    public  STPair getSessionByDatetimeAndName(String filmname, LocalDate date, Time time) {

        Session session;
        Theater theater;

        if (DBConnector.isConnected()) {
            try {

                PreparedStatement statement  = DBConnector.getConnection().prepareStatement(Requests.GET_SESSION_BY_DATETIME_AND_NAME);
                statement.setString(1, filmname);
                Calendar calendar = Calendar.getInstance();
                calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
                statement.setDate(2, new java.sql.Date(calendar.getTimeInMillis()));
                statement.setTime(3, time);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    session = new Session(resultSet.getString("FilmName"), resultSet.getDate("SessionDate"), resultSet.getTime("SessionTime"), resultSet.getInt("TheaterNumber"), resultSet.getInt("idSessions"), resultSet.getInt("OrdCost"), resultSet.getInt("VipCost"));
                    theater = new Theater(resultSet.getInt("TheaterNumber"), resultSet.getInt("SeatsNumber"), resultSet.getInt("SeatsInRow"), resultSet.getInt("VIPRow"));
                    return new STPair(theater, session);
                } else return null;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public  Set<Pair<Integer>> getBookedSeats(Session session) {

        Set<Pair<Integer>> seats = new HashSet<>();

        try {
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.GET_BOOKED_SEATS);
            statement.setInt(1, session.getIdSession());
            ResultSet pairs = statement.executeQuery();

            while (pairs.next())
                seats.add(new Pair<>(pairs.getInt("RowNumber"), pairs.getInt("SeatNumber")));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }

    public Integer getLastOrderId() {

        Integer id = null;
        try {
            ResultSet resultSet = DBConnector.sendRequest(Requests.GET_LAST_ORDER_ID);
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }



}

