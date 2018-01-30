package windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;


public class MainScreenController {



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

        String noRepeatCSS = "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center center;\n" +
                "-fx-background-attachment: fixed;\n" +
                "-fx-background-size: cover;";


        poster1.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
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

}
