package model;

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

        int minWindowWidth = 600;
        int minWindowHeight = 450;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.locale/locale", Locale.getDefault()));
        Parent root = fxmlLoader.load();
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Hello world");
        primaryStage.setScene(new Scene(root, minWindowWidth, minWindowHeight));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
