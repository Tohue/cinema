package windows.controllers.menu;


import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class AdminWindowController extends AbstractController {


    public void openTicketChange() {
        try {
            ScreenStarter.Start("infoEditors/TicketChangeScreen.fxml");
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openFilmChange() {
        try {
            ScreenStarter.Start("infoEditors/FilmChangeScreen.fxml");
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTheaterChange() {
        try {
            ScreenStarter.Start("infoEditors/TheaterChangeWindow.fxml");
            closeThisFuckinWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openSessionChange() {

    }


    public void back() {
        try {
            ScreenStarter.Start("contentScreens/MainScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeThisFuckinWindow();
    }
}
