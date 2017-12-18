package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.ModalityBuilder;

import java.io.IOException;

 class DBLoginScreen {

    public void openLoginScreen() throws IOException {

        Stage primaryStage = new ModalityBuilder().getModalityScreen("DBLogin.fxml");
        primaryStage.show();

    }
}
