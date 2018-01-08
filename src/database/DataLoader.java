package database;

import entities.Film;
import entities.Session;
import entities.Theater;
import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DataLoader {


    private static ObservableList<Film> filmInfoList = FXCollections.observableArrayList();
    private static ObservableList<Session> sessionList = FXCollections.observableArrayList();
    private static ObservableList<Theater> theaterList = FXCollections.observableArrayList();
    private static ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public static ObservableList<Film> getFilmInfoList() {
        return filmInfoList;
    }

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
}
