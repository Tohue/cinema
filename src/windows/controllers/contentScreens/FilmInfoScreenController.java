package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import windows.controllers.AbstractController;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public  class FilmInfoScreenController extends AbstractController {


    @FXML
    Label filmNameLabel;
    @FXML
    Label countryLabel;
    @FXML
    Label lengthLabel;
    @FXML
    Label descField;
    @FXML
    Label genreLabel;
    @FXML
    Button posterBut;
    @FXML
    Pane mainPanel;
    @FXML
    Button buyBtn;
    @FXML
    FlowPane timePane;
    @FXML
    DatePicker datePicker;

    private static Film film;

    private static String desctription;

    private static String country;

    private static int length;

    private static String filmName;

    private static String genre;

    private static Image poster;

    public static void setFilm(Film film) {
        FilmInfoScreenController.film = film;
    }

    public static void setGenre(String genre) {
        FilmInfoScreenController.genre = genre;
    }

    public static void setPoster(Image poster) {
        FilmInfoScreenController.poster = poster;
    }

    public static void setFilmName(String filmName) {
        FilmInfoScreenController.filmName = filmName;
    }

    public static void setDesctription(String desctription) {
        FilmInfoScreenController.desctription = desctription;
    }

    public static void setCountry(String country) {
        FilmInfoScreenController.country = country;
    }

    public static void setLength(int length) {
        FilmInfoScreenController.length = length;
    }

    public void initialize() {

        filmNameLabel.setText(filmName);
        countryLabel.setText(countryLabel.getText() + ": " + country);
        lengthLabel.setText(lengthLabel.getText() + ": " + String.valueOf(length));
        descField.setText(desctription);
        genreLabel.setText(genreLabel.getText() + ": " + genre);
        ImageView posterView = new ImageView(poster);
        posterView.setPreserveRatio(true);
        posterView.fitHeightProperty().bind(mainPanel.heightProperty().multiply(0.9));
        posterBut.setGraphic(posterView);
        setDatePicker();
        setFilmTimesByDate(filmName, datePicker.getValue());

    }


    private void setDatePicker() {

        datePicker.setOnAction(event -> setFilmTimesByDate(filmName, datePicker.getValue()));
        datePicker.setValue(LocalDate.now());

    }

    private void setFilmTimesByDate(String filmName, LocalDate date) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        try {
            System.out.println(new java.sql.Date(calendar.getTimeInMillis()));
            ObservableList<Time> times = DataLoader.getFilmtimesByDate(filmName, new java.sql.Date(calendar.getTimeInMillis()));

            for (Time time : times) {
                Hyperlink timeLink = new Hyperlink();
                timeLink.setText(time.toString());
                timePane.getChildren().add(timeLink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
