package windows.controllers.contentScreens;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import windows.controllers.AbstractController;


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

    private static String desctription;

    private static String country;

    private static int length;

    private static String filmName;

    private static String genre;

    private static Image poster;

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

    }
}
