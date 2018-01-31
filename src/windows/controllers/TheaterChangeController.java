package windows.controllers;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TheaterChangeController {
    @FXML
    TextField addNumberField;
    @FXML
    TextField addCountField;
    @FXML
    ChoiceBox<Integer> editNumberField;
    @FXML
    TextField editCountField;

    HashMap<Integer, Integer> theaters = null;

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        TheaterChangeController.primaryStage = primaryStage;
    }

    public void cancel() {
        primaryStage.close();
    }

    public void initialize() {


        try {
            DataLoader.loadTheaters();
             theaters = DataLoader.getTheatersHashMap();

            Iterator iterator = theaters.entrySet().iterator();
            while (iterator.hasNext()) {
                HashMap.Entry<Integer, Integer> temp = (HashMap.Entry<Integer, Integer>)iterator.next();
                editNumberField.getItems().add(temp.getKey());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        editNumberField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (theaters != null)
                    editCountField.setText(theaters.get(editNumberField.getValue()).toString());
            }
        });

    }

    /**
     * Загрузка нового зала в базу
     * @param actionEvent
     * @throws SQLException
     */
    public void saveCreating(ActionEvent actionEvent) throws SQLException {

        PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_THEATER);
        statement.setInt(1, Integer.parseInt(addNumberField.getText()));
        statement.setInt(2, Integer.parseInt(addCountField.getText()));
        statement.executeUpdate();

    }

    /**
     * Изменение старого зала
     * @param actionEvent
     */
    public void saveEditing(ActionEvent actionEvent) throws SQLException {

        PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.UPDATE_THEATER);
        statement.setInt(1, Integer.parseInt(editCountField.getText()));
        statement.setInt(2, editNumberField.getValue());
        statement.executeUpdate();

    }
}
