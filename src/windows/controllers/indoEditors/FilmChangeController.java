package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Film;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import windows.controllers.AbstractController;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
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
    Spinner<Integer> lengthField;
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
    @FXML
    Label fileNameLabel;
    @FXML
    Label posterError;

    private boolean isSelectedPoster = false;
    private byte[] poster;
    private boolean isChangePoster = false;
    private byte[] newPoster;

    public void initialize() {

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

        if (isChangePoster) {

        }

    }

    public void saveCreating() {

        if (isSelectedPoster) {
            System.out.println("Сохраняем фильм");

            PreparedStatement statement = null;
            try {
                statement = DBConnector.getConnection().prepareStatement(Requests.ADD_FILM);
                statement.setString(1, nameField.getText());
                statement.setInt(2, lengthField.getValue());
                statement.setString(3, countryField.getText());
                statement.setString(4, descField.getText());
                statement.setBlob(5, new SerialBlob(poster));
                statement.setString(6, genreField.getText());
                statement.executeUpdate();
                hidePosterError();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else showPosterError();

        updateInfo();
    }

    private void setSpinners() {

        ObservableList<Film> films = getFilms();

        filmNameEdit.getItems().clear();
        for (Film film : films)
            filmNameEdit.getItems().add(film.getName());

        filmNameEdit.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
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

        });

        lengthField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));


    }


    @Override
    public void updateInfo() {

        setSpinners();

    }

    private void hidePosterError() {
        posterError.setVisible(false);
    }

    private void showPosterError() {
        posterError.setVisible(true);
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

    public void addPoster() {

        Blob blob = null;
        try {
            FileInputStream fstream = new FileInputStream(choosePoster(fileNameLabel));
            byte[] bytes = fstream.readAllBytes();
            poster = bytes;
            isSelectedPoster = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void changePoster() {

        Blob blob = null;
        try {
            FileInputStream fstream = new FileInputStream(choosePoster(fileNameLabel));
            byte[] bytes = fstream.readAllBytes();
            poster = bytes;
            isSelectedPoster = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private File choosePoster(Label label) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор постера");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.img", "*.png", "*.jpg", ".bmp", "*.gif"));
        File f = fileChooser.showOpenDialog(stage);
        label.setText(f.getName());
        return f;

    }

}
