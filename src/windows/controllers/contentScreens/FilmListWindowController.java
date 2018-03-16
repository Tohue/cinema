package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import windows.components.FontLoader;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Контроллер экрана со списком фильмов
 */

public class FilmListWindowController extends AbstractController {

    @FXML
    TableView<Film> filmTable;
    @FXML
    TableColumn<Film, String> nameCol;
    @FXML
    TableColumn<Film, String> countryCol;
    @FXML
    TableColumn<Film, String> descCol;
    @FXML
    TableColumn<Film, Integer> lenCol;
    @FXML
    TableColumn<Film, String> genreCol;

    @FXML
    ComboBox<String> genreField;
    @FXML
    ComboBox<String> countryField;

    @FXML
    Label sortLabel;
    @FXML
    Label title;

    private static ObservableList<Film> primaryList = FXCollections.observableArrayList();

    public void initialize() {

        filmTable.autosize();
        loadFilms();
        setColumnsClickable();
        setSortFields();
        setFonts();

     //   setTextWrap();

    }

    private void loadFilms() {

        ObservableList<Film> observableList = null;

        /**
         * Загрузка списка фильмов, если он пустой
         */
        DataLoader dataLoader = new DataLoader();
        try {
            dataLoader.loadFilmInfo();
            observableList = dataLoader.getFilmInfoList();
            primaryList = dataLoader.getFilmInfoList();
            setSorts();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        filmTable.setItems(observableList);

    }

    private void setColumnsClickable() {


        filmTable.setOnMouseClicked(event -> {


            String country = filmTable.getSelectionModel().getSelectedItem().getCountry();
            int length = filmTable.getSelectionModel().getSelectedItem().getLength();
            String desc = filmTable.getSelectionModel().getSelectedItem().getDescription();
            String genre = filmTable.getSelectionModel().getSelectedItem().getGenre();
            Image poster = filmTable.getSelectionModel().getSelectedItem().getPoster();
            String name = filmTable.getSelectionModel().getSelectedItem().getName();
            System.out.println(poster);

            try {
                ScreenStarter.StartFilmInfoList("contentScreens/FilmInfoScreen.fxml", name, country, length, genre, desc, poster, stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private void setFonts() {

        FontLoader fontLoader = new FontLoader();
        sortLabel.setFont(fontLoader.getBebasReg(30));
        title.setFont(fontLoader.getLemon(50));

    }

    private void setSortFields() {

        DataLoader dataLoader = new DataLoader();
        genreField.getItems().add("");
        countryField.getItems().add("");
        genreField.getItems().addAll(dataLoader.getGenres());
        countryField.getItems().addAll(dataLoader.getCountries());

    }

    private void setTextWrap() {

        descCol.setCellFactory(param -> {
            TableCell<Film, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

    }

    private void setSorts() {

        genreField.setOnAction(event -> filterFilms());
        countryField.setOnAction(event -> filterFilms());

    }

    private void filterFilms() {

        if (emptyField(countryField) && emptyField(genreField))
            filmTable.setItems(primaryList);
        else if (emptyField(countryField))
            filmTable.setItems(FXCollections.observableArrayList(primaryList.stream().filter((p) -> p.getGenre().equals(genreField.getValue())).collect(Collectors.toList())));
        else if (emptyField(genreField))
            filmTable.setItems(FXCollections.observableArrayList(primaryList.stream().filter((p) -> p.getCountry().equals(countryField.getValue())).collect(Collectors.toList())));
        else  filmTable.setItems(FXCollections.observableArrayList(primaryList.stream().filter((p) -> p.getGenre().equals(genreField.getValue()) && p.getCountry().equals(countryField.getValue())).collect(Collectors.toList())));


    }

    private boolean emptyField(ComboBox field) {
        if (field.getValue() == null || field.getValue().equals(""))
            return true;
        else return false;
    }


}
