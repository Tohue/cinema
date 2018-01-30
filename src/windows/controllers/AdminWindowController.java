package windows.controllers;


import javafx.stage.Stage;
import windows.windowStarters.MainScreen;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class AdminWindowController {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        AdminWindowController.primaryStage = primaryStage;
    }

    public void closeThis() {

        new MainScreen().openMainScreen();
       primaryStage.close();

    }

    public void openTicketChange() {

    }

    public void openFilmChange() {
        try {
            FilmChangeController.setPrimaryStage(ScreenStarter.Start("FilmChangeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTheaterChange() {

    }

    public void openSessionChange() {

    }

}
