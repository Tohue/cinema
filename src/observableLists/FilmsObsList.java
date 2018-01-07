package observableLists;

import entities.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmsObsList {
    private ObservableList<Film>  filmList = FXCollections.observableArrayList();

    public void clear() {
        filmList.clear();
    }

    public void add(Film film) {
        filmList.add(film);
    }

    public void remove(Film film) {
        filmList.remove(film);
    }

    public ObservableList<Film> getFilmList() {
        return filmList;
    }
}
