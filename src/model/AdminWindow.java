package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminWindow {

    private static Stage primaryStage = null;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    public void openAdminScreen(Window owner) throws IOException {

        int minWindowWidth = 400;
        int minWindowHeight = 600;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/AdminWindow.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.locale/locale", Locale.getDefault()));
        Parent root = fxmlLoader.load();
        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root, minWindowWidth, minWindowHeight));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(owner);
        primaryStage.show();


    }
}
