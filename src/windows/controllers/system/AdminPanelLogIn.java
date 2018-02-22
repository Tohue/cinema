package windows.controllers.system;

import database.DBConnector;
import database.Requests;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminPanelLogIn extends AbstractController {

    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;

    public void logIn() {

        String login = loginField.getText();
        String password = passwordField.getText();

        if (!login.equals("") && DBConnector.isConnected()) {

            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.CHECK_USER);
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next())
                    if (resultSet.getString(1).equals(password)) {
                        access();
                        break;
                    }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void access() {

        try {
            close();
            ScreenStarter.Start("menu/AdminWindow.fxml", getLastWindow());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
