package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.ModalityBuilder;

import java.io.IOException;

public class DBLoginScreen {

    public void openLoginScreen() throws IOException {

        Stage primaryStage = ModalityBuilder.getModalityScreen("DBLogin.fxml");
        primaryStage.show();

    }
}
