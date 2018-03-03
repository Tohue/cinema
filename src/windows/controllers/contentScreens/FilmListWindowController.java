package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import windows.components.FontLoader;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;
import java.io.IOException;
import java.sql.SQLException;

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

    public void initialize() {

        filmTable.autosize();
        loadFilms();
        setColumnsClickable();
        setSortFields();
        setFonts();

    }

    private void loadFilms() {

        ObservableList<Film>  observableList = null;

        /**
         * Загрузка списка фильмов, если он пустой
         */
        DataLoader dataLoader = new DataLoader();
        try {
            dataLoader.loadFilmInfo();
            observableList = dataLoader.getFilmInfoList();

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
        genreField.setItems(dataLoader.getGenres());
        countryField.setItems(dataLoader.getCountries());

    }

}
