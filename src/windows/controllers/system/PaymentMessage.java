package windows.controllers.system;

import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class PaymentMessage extends AbstractController {

    public  void initialize() {



    }

    public void closeThis() {

        try {
            ScreenStarter.Start("/contentScreens/MainScreen.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
        close();

    }

}
