package windows.windowStarters;

import javafx.application.Application;
import javafx.stage.Stage;
import windows.builders.ModalityBuilder;
import windows.controllers.AdminWindowController;
import windows.controllers.DBLoginController;

public class loginScreen extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage = new ModalityBuilder().getModalityScreen("DBLogin.fxml");
        DBLoginController.setPrimaryStage(primaryStage);
        primaryStage.show();

    }
}
