package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

        filmTable.autosize();
        ObservableList<Film>  observableList = null;

        /**
         * Загрузка списка фильмов, если он пустой
         */
        if (DataLoader.getFilmInfoList().isEmpty()) {
            try {
                DataLoader.loadFilmInfo();
                observableList = DataLoader.getFilmInfoList();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else observableList = DataLoader.getFilmInfoList();

        /**
         * Инициализация колонок таблицы
         */
        numCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        filmTable.setItems(observableList);


        setColumnsClickable();


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


}
