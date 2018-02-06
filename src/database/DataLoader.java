package database;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class DataLoader {


    // Все данные из базы
    private static ObservableList<Film> filmInfoList = FXCollections.observableArrayList();
    private static ObservableList<Session> sessionList = FXCollections.observableArrayList();
    private static ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    private static ObservableList<Order> orderList = FXCollections.observableArrayList();
    private static ObservableList<Integer> orderNumsList = FXCollections.observableArrayList();
    private static HashMap<Integer, Integer> theatersHashMap = new HashMap<>();
    private static ArrayList<Image> postersList = new ArrayList<>();

    // Геттеры списков
    public static ObservableList<Ticket> getTicketList() {
        return ticketList;
    }
    public static ArrayList<Image> getPostersList() {
        return postersList;
    }
    public static ObservableList<Film> getFilmInfoList() {
        return filmInfoList;
    }
    public static ObservableList<Session> getSessionList() {
        return sessionList;
    }
    public static HashMap<Integer, Integer> getTheatersHashMap() {
        return theatersHashMap;
    }
    public static ObservableList<Order> getOrderList() {
        return orderList;
    }
    public static ObservableList<Integer> getOrderNumsList() {
        return orderNumsList;
    }

    /**
     * Загрузка списка фильмов
     * @throws SQLException
     */
    public static void loadFilmInfo() throws SQLException {

        filmInfoList.clear();
        if (DBConnector.isConnected()) {

            ResultSet films = null;
            int i = 1;
            films = DBConnector.sendRequest(Requests.GET_ALL_FILMS);

            while (films.next()) {
                    filmInfoList.add(new Film(i, films.getString(1), films.getString(4), films.getString(6), films.getString(3), films.getInt(2), null));
                    i++;
            }
        }
    }

    /**
     * Загрузка расписания
     * @throws SQLException
     */
    public static void loadSchedule() throws SQLException {

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
    public static void loadPosters() throws SQLException {

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
    public static void loadTheaters() throws SQLException {

        theatersHashMap.clear();
        if (DBConnector.isConnected()) {

            ResultSet theaters = null;
            theaters = DBConnector.sendRequest(Requests.GET_THEATERS);

            while (theaters.next())
                theatersHashMap.put(theaters.getInt(1), theaters.getInt(2));

        }


    }

    /**
     * Загрузка списка билетов
     * @throws SQLException
     */
    public static void loadTickets() throws SQLException {

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
    public static void loadOrders() throws SQLException {

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

    public static ObservableList<Session> getSessionsByDate(Date  date) throws SQLException {

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
}
