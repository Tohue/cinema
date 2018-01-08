package windows.controllers;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import observableLists.FilmsObsList;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;


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


        ObservableList<Film>  observableList = null;

        if (DataLoader.getFilmInfoList().isEmpty()) {
            try {
                DataLoader.loadFilmInfo();
                observableList = DataLoader.getFilmInfoList();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else observableList = DataLoader.getFilmInfoList();

        numCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Film, String>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Film, String>("country"));
        descCol.setCellValueFactory(new PropertyValueFactory<Film, String>("description"));
        lenCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
        filmTable.setItems(observableList);

    }
}
