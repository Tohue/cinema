package windows.windowStarters;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.controllers.contentScreens.FilmInfoScreenController;
import java.io.IOException;


public class ScreenStarter {

    public static void Start(String FXMLName, Stage lastWindow) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.show();
     //   return primaryStage;

    }


    public static void Start(String FXMLName) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName);
        primaryStage.show();
     //   return primaryStage;

    }


    public static void StartFilmInfoList(String FXMLName, String filmName, String country, int length, String genre, String description, Image poster, Stage lastWindow) throws IOException {

        FilmInfoScreenController.setCountry(country);
        FilmInfoScreenController.setLength(length);
        FilmInfoScreenController.setDesctription(description);
        FilmInfoScreenController.setFilmName(filmName);
        FilmInfoScreenController.setPoster(poster);

        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.show();

    }



}
