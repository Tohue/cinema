package windows.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import windows.windowStarters.AdminWindow;

import java.io.IOException;

public class MainScreenController {



    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            new AdminWindow().openAdminScreen(((Node)actionEvent.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
