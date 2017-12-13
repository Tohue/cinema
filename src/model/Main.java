package model;

import builders.FullSizeWindowBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage = FullSizeWindowBuilder.getFullSizeScreen("mainScreen.fxml");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
