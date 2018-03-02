package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import entities.STPair;
import entities.Session;
import entities.Theater;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import windows.components.FontLoader;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;



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
    FlowPane timePane;
    @FXML
    DatePicker datePicker;
    @FXML
    Hyperlink todayLink;
    @FXML
    Hyperlink tomorrowLink;
    @FXML
    AnchorPane infoPane;
    @FXML
    Button backBtn;

    @FXML
    Label noFilmsLabel;

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

        setFonts();
        setMainPoster();
        setDatePicker();
        setInfo();
        setFilmTimesByDate(filmName, datePicker.getValue());
        setLinks();

    }

    private void setInfo() {

        filmNameLabel.setText(filmName);
        countryLabel.setText(countryLabel.getText() + ": " + country);
        lengthLabel.setText(lengthLabel.getText() + ": " + String.valueOf(length));
        descField.setText(desctription);
        genreLabel.setText(genreLabel.getText() + ": " + genre);

    }

    private void setMainPoster() {

        ImageView posterView = new ImageView(poster);
        posterView.setPreserveRatio(true);
        posterView.fitHeightProperty().bind(mainPanel.heightProperty().multiply(0.9));
        posterBut.setGraphic(posterView);

    }

    private void setDatePicker() {

        datePicker.setOnAction(event -> setFilmTimesByDate(filmName, datePicker.getValue()));
        datePicker.setValue(LocalDate.now());

    }

    private void setFilmTimesByDate(String filmName, LocalDate date) {


        datePicker.setValue(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        try {
            System.out.println(new java.sql.Date(calendar.getTimeInMillis()));
            DataLoader dataLoader = new DataLoader();
            ObservableList<Time> times = dataLoader.getFilmtimesByDate(filmName, new java.sql.Date(calendar.getTimeInMillis()));
            hideTimes();
            if (times.size() > 0) {

                for (Time time : times) {

                    Hyperlink timeLink = new Hyperlink();
                    timeLink.setOnAction(event -> {
                        try {
                            STPair pair = dataLoader.getSessionByDatetimeAndName(filmName, datePicker.getValue(), time);
                            Session selectedSession = pair.getSession();
                            Theater sessionTheater = pair.getTheater();
                            if (selectedSession != null) {
                                ScreenStarter.StartSelectSeats(selectedSession, sessionTheater, stage);
                                close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    timeLink.setText(time.toString().substring(0, 5));
                    timePane.getChildren().add(timeLink);
                }
            }

            else
                noFilmsLabel.setVisible(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void hideTimes() {

        noFilmsLabel.setVisible(false);
        timePane.getChildren().clear();
    }

    private void setLinks() {


        todayLink.setOnAction(event -> setFilmTimesByDate(filmName, LocalDate.now()));

        tomorrowLink.setOnAction(event -> setFilmTimesByDate(filmName, LocalDate.now().plusDays(1)));

    }

    private void setFonts() {

        FontLoader fontLoader = new FontLoader();
        backBtn.setFont(fontLoader.getBebasReg(40));
        filmNameLabel.setFont(fontLoader.getLemon(50));
    }

}
