package windows.controllers.indoEditors;

import database.DBConnector;
import database.Requests;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import windows.controllers.AbstractController;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmChangeController extends AbstractController implements infoEditor {

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

    @FXML
    Label errorSaveLabel;
    @FXML
    Label errorEditLabel;


    public void saveEditing() {

    }

    public void saveCreating() {

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

            PreparedStatement statement = null;
            try {

                statement = DBConnector.getConnection().prepareStatement(Requests.ADD_FILM_WITHOUT_POSTERS);
                statement.setString(1, nameField.getText());
                statement.setInt(2, Integer.parseInt(lengthField.getText()));
                statement.setString(3, countryField.getText());
                statement.setString(4, descField.getText());
                statement.setString(5, genreField.getText());
                statement.executeUpdate();
                hideErrors();

            } catch (SQLException e) {
                creatingError();
                e.printStackTrace();
            }
        }

        updateInfo();
    }



    @Override
    public void updateInfo() {

    }

    @Override
    public void creatingError() {
        errorSaveLabel.setVisible(true);
    }

    @Override
    public void editingError() {
        errorEditLabel.setVisible(true);
    }

    private void hideErrors() {
        errorEditLabel.setVisible(false);
        errorSaveLabel.setVisible(false);
    }
}
