package windows.controllers.menu;


import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class AdminWindowController extends AbstractController {


    public void openTicketChange() {
        try {
            ScreenStarter.Start("infoEditors/TicketChangeScreen.fxml", stage);
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openFilmChange() {
        try {
            ScreenStarter.Start("infoEditors/FilmChangeScreen.fxml", stage);
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openOrdersChange() {
        try {
            ScreenStarter.Start("infoEditors/OrderChangeScreen.fxml", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTheaterChange() {
        try {
            ScreenStarter.Start("infoEditors/TheaterChangeWindow.fxml", stage);
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openSessionChange() {

        try {
            ScreenStarter.Start("infoEditors/SessionChangeScreen.fxml", stage);
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openOrderChange() {

    }

//    public void back() {
//        try {
//            ScreenStarter.Start("contentScreens/MainScreen.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        closeThisFuckinWindow();
//    }
}
