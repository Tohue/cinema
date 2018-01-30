package windows.windowStarters;

import config.DBPropertiesParser;
import windows.builders.FullSizeWindowBuilder;
import javafx.stage.Stage;
import windows.controllers.MainScreenController;
import java.io.File;


public class MainScreen {

    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void openMainScreen() {




        try {
            primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("mainScreen.fxml");
            MainScreenController.setPrimaryStage(primaryStage);
            primaryStage.show();
        }
         catch (Exception e) {
            e.printStackTrace();
         }
    }


}
