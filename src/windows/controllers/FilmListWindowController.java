package windows.controllers;

import entities.Film;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import observableLists.FilmsObsList;


public class FilmListWindowController {

    @FXML
    TableView<Film> filmTable;
    @FXML
    TableColumn<Film, Integer> numCol;
    @FXML
    TableColumn<Film, String> nameCol;
    @FXML
    TableColumn<Film, String> countryCol;
    @FXML
    TableColumn<Film, String> descCol;
    @FXML
    TableColumn<Film, Integer> lenCol;
    @FXML
    TableColumn<Film, Object> buyCol;


    public void initialize() {

        FilmsObsList list = new FilmsObsList();
        ObservableList<Film>  observableList = list.getFilmList();
        observableList.add(new Film("aa0", "aa", "d0", "dsd", 324, null));


        nameCol.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Film, String>("country"));
        descCol.setCellValueFactory(new PropertyValueFactory<Film, String>("description"));
        lenCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
        filmTable.setItems(observableList);
    }
}
