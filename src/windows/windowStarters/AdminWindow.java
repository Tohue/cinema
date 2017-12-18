package windows.windowStarters;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import windows.builders.ModalityBuilder;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminWindow {

    private static Stage primaryStage = null;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    public void openAdminScreen(Window owner) throws IOException {


        primaryStage = new ModalityBuilder().getModalityScreen("AdminWindow.fxml");
        primaryStage.show();

    }
}
