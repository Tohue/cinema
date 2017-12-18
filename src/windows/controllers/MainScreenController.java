package windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import windows.windowStarters.AdminWindow;
import windows.windowStarters.FilmListWindow;
import java.io.IOException;


public class MainScreenController{

    @FXML
    GridPane postersPane;

    @FXML
    AnchorPane pane1;

    @FXML
    ImageView image1;

    private static Stage primaryStage = null;

    public static void setPrimaryStage(Stage primaryStage) {
        MainScreenController.primaryStage = primaryStage;
    }

    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            new AdminWindow().openAdminScreen(((Node)actionEvent.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void openSchedule(ActionEvent actionEvent) {

    }

    public void openFilmList(ActionEvent actionEvent) {

        new FilmListWindow().openFilmListScreen();
        primaryStage.close();
    }



}
