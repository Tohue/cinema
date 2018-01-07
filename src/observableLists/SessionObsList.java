package observableLists;

import entities.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessionObsList {
    ObservableList<Session> sessionList = FXCollections.observableArrayList();

    public void clear() {
        sessionList.clear();
    }

    public void add(Session film) {
        sessionList.add(film);
    }

    public void remove(Session film) {
        sessionList.remove(film);
    }

    public ObservableList<Session> getFilmList() {
        return sessionList;
    }

}
