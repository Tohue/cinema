package windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DBLoginController {

    @FXML
    TextField logField;

    @FXML
    PasswordField passField;

    public void connect(ActionEvent actionEvent) {
        System.out.println(logField.getText());
        System.out.println(passField.getText());

    }
}
