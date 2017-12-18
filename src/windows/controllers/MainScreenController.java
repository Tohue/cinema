package windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import windows.windowStarters.AdminWindow;
import windows.windowStarters.FilmListWindow;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {

    @FXML
    GridPane postersPane;

    @FXML
    AnchorPane pane1;

    @FXML
    ImageView image1;

    ArrayList<ImageView> posters = new ArrayList<>();

    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            new AdminWindow().openAdminScreen(((Node)actionEvent.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {

        
    }

    public void openSchedule(ActionEvent actionEvent) {

    }

    public void openFilmList(ActionEvent actionEvent) {
        new FilmListWindow().openFilmListScreen();
    }
}
