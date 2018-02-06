package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class TheaterChangeController extends AbstractController implements infoEditor {

    @FXML
    TextField addNumberField;
    @FXML
    TextField addCountField;
    @FXML
    ChoiceBox<Integer> editNumberField;
    @FXML
    TextField editCountField;

    @FXML
    Label errorSaveLabel;
    @FXML
    Label errorEditLabel;

    private HashMap<Integer, Integer> theaters = null;

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




        editNumberField.setOnAction(event -> {
            if (theaters != null)
                editCountField.setText(theaters.get(editNumberField.getValue()).toString());
        });

        updateInfo();
    }

    /**
     * Загрузка нового зала в базу
     * @throws SQLException
     */
    public void saveCreating() {

        try {

            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_THEATER);
            statement.setInt(1, Integer.parseInt(addNumberField.getText()));
            statement.setInt(2, Integer.parseInt(addCountField.getText()));
            statement.executeUpdate();
            hideErrors();
            updateInfo();

        } catch (SQLException e) {
            creatingError();
            e.printStackTrace();
        }

    }

    @Override
    public void updateInfo() {

        editNumberField.getItems().clear();
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

    }

    @Override
    public void creatingError() {
        errorSaveLabel.setVisible(true);
    }

    @Override
    public void editingError() {
        errorEditLabel.setVisible(true);
    }

    private void hideErrors () {
        errorEditLabel.setVisible(false);
        errorSaveLabel.setVisible(false);
    }

    /**
     * Изменение старого зала
     */
    public void saveEditing()  {

        PreparedStatement statement = null;
        try {
            statement = DBConnector.getConnection().prepareStatement(Requests.UPDATE_THEATER);

            statement.setInt(1, Integer.parseInt(editCountField.getText()));
            statement.setInt(2, editNumberField.getValue());
            statement.executeUpdate();
            updateInfo();
            hideErrors();
        } catch (SQLException e) {
            editingError();
            e.printStackTrace();
        }

    }

    @Override
    public void closeThisFuckinWindow() {
        try {
            ScreenStarter.Start("menu/AdminWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.closeThisFuckinWindow();
    }
}
