package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;


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
    @FXML
    Hyperlink todayLink;
    @FXML
    Hyperlink tomorrowLink;

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
        setLinks();

    }


    private void setDatePicker() {

        datePicker.setOnAction(event -> setFilmTimesByDate(filmName, datePicker.getValue()));
        datePicker.setValue(LocalDate.now());

    }

    private void setFilmTimesByDate(String filmName, LocalDate date) {

        hideTimes();
        datePicker.setValue(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        try {
            System.out.println(new java.sql.Date(calendar.getTimeInMillis()));
            ObservableList<Time> times = DataLoader.getFilmtimesByDate(filmName, new java.sql.Date(calendar.getTimeInMillis()));

            if (times.size() > 0) {


                for (Time time : times) {

                    Hyperlink timeLink = new Hyperlink();
                    timeLink.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                ScreenStarter.StartSelectSeats(null, stage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    timeLink.setText(time.toString());
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

        todayLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFilmTimesByDate(filmName, LocalDate.now());
            }
        });

        tomorrowLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFilmTimesByDate(filmName, LocalDate.now().plusDays(1));
            }
        });

    }
}
