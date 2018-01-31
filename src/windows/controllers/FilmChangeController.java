package windows.controllers;

import com.mysql.jdbc.Blob;
import database.DBConnector;
import database.DataLoader;
import database.Requests;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmChangeController {

    @FXML
    TextField nameField;
    @FXML
    TextField countryField;
    @FXML
    TextField genreField;
    @FXML
    TextField lengthField;
    @FXML
    TextArea descField;

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        FilmChangeController.primaryStage = primaryStage;
    }

    public void closeThis() {
        primaryStage.close();
    }

    public void saveEditing() {

    }

    public void saveCreating() throws SQLException, IOException {

        System.out.println("Ща будем сохранять фильм");
        if (DBConnector.isConnected()) {
            System.out.println("Сохраняем фильм");
//            InputStream inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\Erik\\IdeaProjects\\Кириллов\\Cinema\\cinema\\resources\\images\\1.png"));
//            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_FILM);
//            statement.setString(1, nameField.getText());
//            statement.setInt(2, Integer.parseInt(lengthField.getText()));
//            statement.setString(3, countryField.getText());
//            statement.setString(4, descField.getText());
//
//
//            statement.setBlob(5, inputStream);
//            statement.setString(6, genreField.getText());
//            statement.executeUpdate();

            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_FILM_WITHOUT_POSTERS);
            statement.setString(1, nameField.getText());
            statement.setInt(2, Integer.parseInt(lengthField.getText()));
            statement.setString(3, countryField.getText());
            statement.setString(4, descField.getText());
            statement.setString(5, genreField.getText());
            statement.executeUpdate();

        }

    }
}
