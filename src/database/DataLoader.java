package database;

import entities.Film;
import entities.Session;
import entities.Theater;
import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DataLoader {


    private static ObservableList<Film> filmInfoList = FXCollections.observableArrayList();
    private static ObservableList<Session> sessionList = FXCollections.observableArrayList();
    private static ObservableList<Theater> theaterList = FXCollections.observableArrayList();
    private static ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    private static HashMap<Integer, Integer> theatersHashMap = new HashMap<>();
    private static ArrayList<Image> postersList = new ArrayList<>();

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

    /**
     * Загрузка списка фильмов
     * @throws SQLException
     */
    public static void loadFilmInfo() throws SQLException {

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

        if (DBConnector.isConnected()) {

            ResultSet sessions = null;
            int i = 1;
            sessions = DBConnector.sendRequest(Requests.GET_SCHEDULE);

            while (sessions.next()) {

                sessionList.add(new Session(sessions.getString(2), sessions.getDate(4), 0, sessions.getInt(3), sessions.getInt(1)));
                i++;
            }
        }

    }

    /**
     * Загрузка постеров
     * @throws SQLException
     */
    public static void loadPosters() throws SQLException {

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

    public static void loadTheaters() throws SQLException {

        if (DBConnector.isConnected()) {

            ResultSet theaters = null;
            theaters = DBConnector.sendRequest(Requests.GET_THEATERS);

            while (theaters.next())
                theatersHashMap.put(theaters.getInt(1), theaters.getInt(2));

        }


    }

}
