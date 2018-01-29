package windows.controllers;

import config.DBConfig;
import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import windows.windowStarters.BackWindow;
import windows.windowStarters.MainScreen;

import java.sql.Connection;
import java.sql.SQLException;

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


        DBConnector.setValues(logField.getText(), passField.getText());
        try {
            Connection connection = DBConnector.doConnection();
            if (connection != null) {
                primaryStage.close();

                new BackWindow().openBack();
                new MainScreen().openMainScreen();
            } else {
                logField.clear();
                passField.clear();
                errorLabel.setVisible(true);
            }
        } catch (Exception e) {
            errorLabel.setVisible(true);
            System.out.println("URL:" + DBConfig.getDATABASEURL());
            System.out.println("Driver:" + DBConfig.getDRIVER());
            System.out.println("Login:" + logField.getText());
            System.out.println("Password:" + passField.getText());
        }

    }
}
