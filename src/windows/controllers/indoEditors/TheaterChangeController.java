package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Theater;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import windows.controllers.AbstractController;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    Spinner<Integer> editCountField;
    @FXML
    Spinner<Integer> editSeatsInRowField;
    @FXML
    Spinner<Integer> editVIPRowField;
    @FXML
    TextField addSeatsInRowField;
    @FXML
    TextField addVIPRowField;

    @FXML
    Label errorSaveLabel;
    @FXML
    Label errorEditLabel;

    private ArrayList<Theater> theaters = null;

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
            if (theaters != null) {
                int i = 0;
                int currTheaterIndex = 0;
                for (Theater theater : theaters) {
                    if (theater.getTheaterNumber() == editNumberField.getValue()) {
                        currTheaterIndex = i;
                        break;
                    }
                    i++;
                }
                editCountField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, theaters.get(currTheaterIndex).getSeatsNumber(), 1));
                editSeatsInRowField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, theaters.get(currTheaterIndex).getSeatsInRow(), 1));
                editVIPRowField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, theaters.get(currTheaterIndex).getVIPRowNumber(), 1));
            }
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
            statement.setInt(3, Integer.parseInt(addSeatsInRowField.getText()));
            statement.setInt(4, Integer.parseInt(addVIPRowField.getText()));
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
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadTheaters();
            theaters = dataLoader.getTheatersList();


            for (Theater theater : theaters)
                editNumberField.getItems().add(theater.getTheaterNumber());

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

    @Override
    public void delete() {

        if (editNumberField.getValue() != null) {
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_THEATER);
                System.out.println(1);
                statement.setInt(1, editNumberField.getValue());
                System.out.println(2);
                statement.executeUpdate();
                System.out.println(3);
                updateInfo();
                System.out.println(4);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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

            statement.setInt(1, editCountField.getValue());
            statement.setInt(2, editNumberField.getValue());
            statement.setInt(3, editSeatsInRowField.getValue());
            statement.setInt(4, editVIPRowField.getValue());
            statement.executeUpdate();
            updateInfo();
            hideErrors();
        } catch (SQLException e) {
            editingError();
            e.printStackTrace();
        }

    }


}
