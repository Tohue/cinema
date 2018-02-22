package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Film;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import windows.controllers.AbstractController;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    ChoiceBox<String> filmNameEdit;
    @FXML
    TextArea filmDescEdit;
    @FXML
    TextField genreEdit;
    @FXML
    TextField countryEdit;
    @FXML
    Spinner<Integer> lengthEdit;

    @FXML
    Label errorSaveLabel;
    @FXML
    Label errorEditLabel;


    public void initialize() {

        ObservableList<Film> films = getFilms();
        filmNameEdit.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Film film = null;
                for (Film film1 : films)
                    if (film1.getName().equals(filmNameEdit.getValue())) {
                        film = film1;
                        break;
                    }

                if (film != null) {
                    countryEdit.setText(film.getCountry());
                    filmDescEdit.setText(film.getDescription());
                    genreEdit.setText(film.getGenre());
                    lengthEdit.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, film.getLength()));
                }

            }
        });
        updateInfo();

    }

    private ObservableList<Film> getFilms() {

        DataLoader dataLoader = new DataLoader();
        try {
            dataLoader.loadFilmInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataLoader.getFilmInfoList();

    }


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

        ObservableList<Film> films = getFilms();

        filmNameEdit.getItems().clear();
        for (Film film : films)
            filmNameEdit.getItems().add(film.getName());


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

        if (nameField.getText() != null && nameField.getText() != "" && DBConnector.isConnected()) {

            try {

                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_FILM);
                statement.setString(1, filmNameEdit.getValue());
                statement.executeUpdate();
                clearEditFields();
                updateInfo();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void hideErrors() {
        errorEditLabel.setVisible(false);
        errorSaveLabel.setVisible(false);
    }

    private void clearEditFields() {

        filmNameEdit.getItems().clear();
        countryEdit.setText("");
        filmDescEdit.setText("");
        lengthEdit.getValueFactory().setValue(1);

    }
}
