package windows.controllers;

import javafx.stage.Stage;

public class FilmChangeController {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        FilmChangeController.primaryStage = primaryStage;
    }

    public void closeThis() {
        primaryStage.close();
    }

    public void saveEditing() {

    }

    public void saveCreating() {

    }
}
