package windows.windowStarters;

import config.DBPropertiesParser;
import windows.builders.FullSizeWindowBuilder;
import javafx.stage.Stage;
import windows.controllers.MainScreenController;
import java.io.File;


public class MainScreen {

    public void openMainScreen() throws Exception{

        Stage primaryStage;



        primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("mainScreen.fxml");
        MainScreenController.setPrimaryStage(primaryStage);
        primaryStage.show();

    }


}
