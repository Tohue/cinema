package windows.windowStarters;


import entities.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.components.SeatView;
import windows.controllers.contentScreens.BuyScreenController;
import windows.controllers.contentScreens.FilmInfoScreenController;
import windows.controllers.contentScreens.SelectSeatsController;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.io.IOException;
import java.util.ArrayList;


public class ScreenStarter {

    private static  final double minWindowHeight = 720;
    private static final double minWindowWidth = 1080;

    public static void Start(String FXMLName, Stage lastWindow) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }


    public static void Start(String FXMLName) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();
     //   return primaryStage;

    }


    public static void StartFilmInfoList(String FXMLName, String filmName, String country, int length, String genre, String description, Image poster, Stage lastWindow) throws IOException {

        FilmInfoScreenController.setCountry(country);
        FilmInfoScreenController.setLength(length);
        FilmInfoScreenController.setDesctription(description);
        FilmInfoScreenController.setFilmName(filmName);
        FilmInfoScreenController.setPoster(poster);
        FilmInfoScreenController.setGenre(genre);
        FilmInfoScreenController.setFilm(new Film(0 , filmName, description, genre, country, length, poster));
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }

    public static void StartFilmInfoList(Film film, Stage lastWindow) throws IOException {

        String FXMLName ="/ContentScreens/FilmInfoScreen.fxml";
        FilmInfoScreenController.setCountry(film.getCountry());
        FilmInfoScreenController.setLength(film.getLength());
        FilmInfoScreenController.setDesctription(film.getDescription());
        FilmInfoScreenController.setFilmName(film.getName());
        FilmInfoScreenController.setPoster(film.getPoster());
        FilmInfoScreenController.setFilm(film);
        FilmInfoScreenController.setGenre(film.getGenre());
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }


    public static void StartSelectSeats(Session session, Theater theater, Stage lastWindow) throws IOException {

        String FXMLName ="/ContentScreens/SelectSeatsScreen.fxml";
        SelectSeatsController.setSession(session);
        SelectSeatsController.setTheater(theater);
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();
    }

    public static void StartGoodBuyingScreen(Session session, int ordNum, int vipNum, int sum, ArrayList<SeatView> seats, Stage lastWindow) throws IOException {

        String FXMLName ="contentScreens/BuyScreen.fxml";
        BuyScreenController.setSession(session);
        BuyScreenController.setOrdTicketNum(ordNum);
        BuyScreenController.setVipTicketNum(vipNum);
        BuyScreenController.setSum(sum);
        BuyScreenController.setSeatsCoordsList(seats);
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.setMinHeight(minWindowHeight);
        primaryStage.setMinWidth(minWindowWidth);
        primaryStage.show();

    }




}
