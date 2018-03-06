package windows.controllers.system;

import config.DBConfig;
import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;
import java.sql.Connection;


public class DBLoginController  extends AbstractController {

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

    public void connect(ActionEvent actionEvent)  {


        DBConnector.setValues(logField.getText(), passField.getText());
        try {
            Connection connection = DBConnector.doConnection();
            if (connection != null) {
                closeThisFuckinWindow();
                ScreenStarter.Start("contentScreens/mainScreen.fxml", stage);
            } else {
                logField.clear();
                passField.clear();
                errorLabel.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setVisible(true);
            System.out.println("URL:" + DBConfig.getDATABASEURL());
            System.out.println("Driver:" + DBConfig.getDRIVER());
            System.out.println("Login:" + logField.getText());
            System.out.println("Password:" + passField.getText());
        }

    }
}
