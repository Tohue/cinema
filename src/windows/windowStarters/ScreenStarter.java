package windows.windowStarters;


import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;

import java.io.IOException;


public class ScreenStarter {

    public static Stage Start(String FXMLName, Stage lastWindow) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName, lastWindow);
        primaryStage.show();
        return primaryStage;

    }


    public static Stage Start(String FXMLName) throws IOException {


        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen(FXMLName);
        primaryStage.show();
        return primaryStage;

    }



}
