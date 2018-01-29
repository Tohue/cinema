package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.controllers.ScreenController;

import java.io.IOException;


public class ScreenStarter {

    public static void Start(String FXMLName) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName);
        primaryStage.show();

    }

    public static void Start(ScreenController controller, String FXMLName) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName);
        primaryStage.show();
        controller.setPrimaryStage(primaryStage);


    }

}
