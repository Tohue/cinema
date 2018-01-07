package observableLists;

import entities.Theater;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TheaterObsList {
    ObservableList<Theater> theaterList = FXCollections.observableArrayList();

    public void clear() {
        theaterList.clear();
    }

    public void add(Theater film) {
        theaterList.add(film);
    }

    public void remove(Theater film) {
        theaterList.remove(film);
    }

    public ObservableList<Theater> getFilmList() {
        return theaterList;
    }
}
