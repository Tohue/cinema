package windows.controllers;

import database.DataLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.windowStarters.ScreenStarter;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainScreenController {

    ArrayList<Button> postersView = new ArrayList<>();

    @FXML
    GridPane postersPane;

    @FXML
    AnchorPane pane1;

    @FXML
    Label mainTitle;

    @FXML
    Button poster1;
    @FXML
    Button poster2;
    @FXML
    Button poster3;
    @FXML
    Button poster4;
    @FXML
    Button poster5;
    @FXML
    Button poster6;
    @FXML
    Button poster7;
    @FXML
    Button poster8;
    @FXML
    Button poster9;
    @FXML
    Button poster10;
    @FXML
    Button poster11;
    @FXML
    Button poster12;

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        MainScreenController.primaryStage = primaryStage;
    }

    /**
     * кнопка открытия админки
     * @param actionEvent
     */
    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("AdminWindow.fxml");
            AdminWindowController.setPrimaryStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }


    /**
     * Кнопка открытия расписания
     * @param actionEvent
     */
    public void openSchedule(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("ScheduleScreen.fxml");
            ScheduleScreenController.setPrimaryStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }

    /**
     * Кнопка открытия списка фильмов
     * @param actionEvent
     */
    public void openFilmList(ActionEvent actionEvent) {

        try {
            Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("FilmListWindow.fxml");
            FilmListWindowController.setPrimaryStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }

    public void initialize() {

        ArrayList<Image> posters = null;
        try {
            DataLoader.loadPosters();
            posters = DataLoader.getPostersList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (posters.size() < 11) {

        }

        String noRepeatCSS = "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center center;\n" +
                "-fx-background-attachment: fixed;\n" +
                "-fx-background-size: cover;";


//        Image image = new Image("/images/1.png");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        Button button = new Button("");
//        button.setGraphic(imageView);
//        pane1.getChildren().addAll(button);
//        button.getGraphic().setStyle(noRepeatCSS);
//        button.setStyle(noRepeatCSS);
        poster2.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster3.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster4.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster5.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster6.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster7.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster8.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster9.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster10.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster11.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
        poster12.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);



    }


    private void viewPosters() {

    }

}
