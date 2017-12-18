package windows.controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import windows.windowStarters.MainScreen;

import java.sql.Connection;

public class DBLoginController {

    @FXML
    TextField logField;

    @FXML
    PasswordField passField;

    @FXML
    Label errorLabel;

    private static Stage primaryStage = null;

    public static void setPrimaryStage(Stage primaryStage) {
        DBLoginController.primaryStage = primaryStage;
    }

    public void connect(ActionEvent actionEvent) throws Exception {


        DBConnector connector = new DBConnector(logField.getText(), passField.getText());
        try {
            Connection connection = connector.getConnection();
            if (connection != null) {
                primaryStage.close();
                new MainScreen().openMainScreen();
            } else {
                logField.clear();
                passField.clear();
                errorLabel.setVisible(true);
            }
        } catch (Exception e) {
            errorLabel.setVisible(true);
        }

    }
}
