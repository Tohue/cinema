package windows.controllers;

import javafx.stage.Stage;

public abstract class ScreenController {

    static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        ScreenController.primaryStage = primaryStage;
    }

}
