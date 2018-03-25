package windows.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import windows.components.FontLoader;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class PaymentMessage extends AbstractController {

    @FXML
    Label title1;
    @FXML
    Label title2;
    @FXML
    Label title3;


    public  void initialize() {

        setFonts();

    }

    private void setFonts() {

        FontLoader fontLoader = new FontLoader();
        title1.setFont(fontLoader.getBebasReg(40));
        title2.setFont(fontLoader.getBebasReg(40));
        title3.setFont(fontLoader.getBebasThin(40));

    }

    public void closeThis() {

        try {
            ScreenStarter.Start("contentScreens/mainScreen.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
        close();

    }

}
