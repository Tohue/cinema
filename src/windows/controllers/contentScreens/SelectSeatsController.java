package windows.controllers.contentScreens;

import entities.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import windows.controllers.AbstractController;


public class SelectSeatsController extends AbstractController {

    private static Session session;

    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane infoPane;
    @FXML
    FlowPane seatsPane;
    @FXML
    Label theaterNumLabel;
    @FXML
    Button acceptButton;

    public static void setSession(Session session) {
        SelectSeatsController.session = session;
    }
}
