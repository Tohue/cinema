package windows.controllers.indoEditors;


import database.DBConnector;
import database.DataLoader;
import database.Requests;

import entities.Session;
import entities.Theater;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class SessionChangeController extends AbstractController implements infoEditor {

    // Элементы управления
    @FXML
    Spinner<Integer> numSpin;
    @FXML
    Spinner<Integer> theaterSpin;
    @FXML
    ChoiceBox<String> filmsField;
    @FXML
    DatePicker datePicker;
    @FXML
    Slider hourSlider;
    @FXML
    Slider minuteSlider;
    @FXML
    Label hourLabel;
    @FXML
    Label minuteLabel;
    @FXML
    Spinner<Integer> standartPriceSpinner;
    @FXML
    Spinner<Integer> vipPriceSpinner;

    // Таблица
    @FXML
    TableView<Session> sessionTable;
    @FXML
    TableColumn<Session, Integer> numberCol;
    @FXML
    TableColumn<Session, String> filmCol;
    @FXML
    TableColumn<Session, Integer> theaterCol;
    @FXML
    TableColumn<Session, Date> dateCol;
    @FXML
    TableColumn<Session, String> timeCol;
    @FXML
    TableColumn<Session, Integer> standartPriceCol;
    @FXML
    TableColumn<Session, Integer> vipPriceCol;

    @FXML
    Label createSaveErrorLabel;
    @FXML
    Label editSaveErrorLabel;

    ObservableList<Session> sessionsInfoList = null;
    ArrayList<Integer> changedRows = new ArrayList<>();

    public void initialize() {


        updateInfo();  // должно выполниться первым
        setSliders();
        setFields();
        setTableEditable();

        numberCol.setCellValueFactory(new PropertyValueFactory<>("idSession"));
        filmCol.setCellValueFactory(new PropertyValueFactory<>("filmName"));
        theaterCol.setCellValueFactory(new PropertyValueFactory<>("theaterNumber"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        standartPriceCol.setCellValueFactory(new PropertyValueFactory<>("standartCost"));
        vipPriceCol.setCellValueFactory(new PropertyValueFactory<>("vipCost"));
    }

    @Override
    public void saveEditing() {

        Iterator<Integer> iterator = changedRows.iterator();
        boolean error = false;


        while (iterator.hasNext()) {

            int id = iterator.next();

            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.UPDATE_SCHEDULE);
                statement.setString(1,((Session)sessionTable.getItems().get(id)).getFilmName());
                System.out.println(((Session)sessionTable.getItems().get(id)).getFilmName());
                statement.setInt(2, ((Session)sessionTable.getItems().get(id)).getTheaterNumber());
                statement.setDate(3, ((Session)sessionTable.getItems().get(id)).getSessionDate());
                statement.setInt(4, ((Session)sessionTable.getItems().get(id)).getStandartCost());
                statement.setInt(5, ((Session)sessionTable.getItems().get(id)).getVipCost());
                statement.setTime(6, getTimeFromString(((Session)sessionTable.getItems().get(id)).getSessionTime()));
                statement.setInt(7, ((Session)sessionTable.getItems().get(id)).getIdSession());
                statement.executeUpdate();
            } catch (SQLException e) {
                error = true;
                e.printStackTrace();
            }
        }

        if (!error)
            hideErrors();
        else editingError();

        updateInfo();
        changedRows.clear();
    }

    @Override
    public void saveCreating() {

        if (DBConnector.isConnected()) {
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_SESSION);
                statement.setInt(1, numSpin.getValue());
                statement.setString(2, filmsField.getValue());
                statement.setInt(3, theaterSpin.getValue());
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getDayOfMonth());
                statement.setDate(4, new java.sql.Date(calendar.getTimeInMillis()));
                statement.setInt(5, standartPriceSpinner.getValue());
                statement.setInt(6, vipPriceSpinner.getValue());
                statement.setTime(7, getSessionTime());
                statement.executeUpdate();
                hideErrors();
                numSpin.increment();
                updateInfo();
            } catch (SQLException e) {
                creatingError();
                e.printStackTrace();
            }
        }
    }
    @Override
    public void updateInfo() {


        sessionTable.getItems().clear();
        DataLoader dataLoader = new DataLoader();

        try {

            dataLoader.loadSchedule();
            sessionsInfoList = dataLoader.getSessionList();
        } catch (SQLException e) {
            e.printStackTrace();
            if (dataLoader.getSessionList() != null)
                sessionsInfoList = dataLoader.getSessionList();
        }

        sessionTable.setItems(sessionsInfoList);

    }

    @Override
    public void creatingError() {
        createSaveErrorLabel.setVisible(true);
    }

    @Override
    public void editingError() {
        editSaveErrorLabel.setVisible(true);
    }

    @Override
    public void delete() {

        if (sessionTable.getSelectionModel().getSelectedItems() != null) {

                try {
                    PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_SESSION);
                    statement.setInt(1, sessionTable.getSelectionModel().getSelectedItem().getIdSession());
                    statement.executeUpdate();
                    updateInfo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }



        }


    }

    private void hideErrors() {
        createSaveErrorLabel.setVisible(false);
        editSaveErrorLabel.setVisible(false);
    }


    private void setSliders() {

        hourSlider.setShowTickLabels(true);
        hourSlider.setShowTickMarks(true);
        hourSlider.setMin(0);
        hourSlider.setMax(23);
        hourSlider.setMajorTickUnit(12);
        hourSlider.setMinorTickCount(6);

        minuteSlider.setShowTickLabels(true);
        minuteSlider.setShowTickMarks(true);
        minuteSlider.setMin(0);
        minuteSlider.setMax(59);
        minuteSlider.setMajorTickUnit(30);
        minuteSlider.setMinorTickCount(2);


        minuteSlider.valueProperty().addListener((observable, oldValue, newValue) -> minuteLabel.setText(String.valueOf(Math.round(minuteSlider.getValue()))));

        hourSlider.valueProperty().addListener((observable, oldValue, newValue) -> hourLabel.setText(String.valueOf(Math.round(hourSlider.getValue()))));




    }

    private void setFields() {

        DataLoader dataLoader = new DataLoader();
        if (dataLoader.getFilmNames() == null || dataLoader.getFilmNames().size() == 0) {
            try {
                dataLoader.loadFilmNames();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        filmsField.setItems(dataLoader.getFilmNames());


        numSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(sessionsInfoList.get(sessionsInfoList.size() - 1).getIdSession() + 1, 1000));

        if (dataLoader.getTheatersList() == null || dataLoader.getTheatersList().size() == 0)
            try {
                dataLoader.loadTheaters();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        ArrayList<Integer> list = new ArrayList<>();
        for (Theater k : dataLoader.getTheatersList())
            list.add(k.getTheaterNumber());

        theaterSpin.setValueFactory(new SpinnerValueFactory<>() {

            int i = 0;

            @Override
            public void decrement(int steps) {

                if (i - 1 >= 0) {
                    i--;
                    setValue(list.get(i));
                }

            }

            @Override
            public void increment(int steps) {

                if (i + 1 < list.size()) {
                    i++;
                    setValue(list.get(i));
                }
            }
        });
        theaterSpin.increment();
        theaterSpin.decrement();


        standartPriceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10));
        vipPriceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10));

    }

    private Time getSessionTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0,0, Integer.parseInt(hourLabel.getText()), Integer.parseInt(minuteLabel.getText()));
        return new Time(calendar.getTimeInMillis());
    }

    private Time getTimeFromString(String string) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0,0, Integer.parseInt(string.substring(0, 2)), Integer.parseInt(string.substring(string.length() - 2, string.length())));
        System.out.println(new Time(calendar.getTimeInMillis()));
        return new Time(calendar.getTimeInMillis());

    }

    private void setTableEditable() {

        DataLoader dataLoader = new DataLoader();

        standartPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        vipPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        try {
            dataLoader.loadFilmNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> filmNames = dataLoader.getFilmNames();
        filmCol.setCellFactory(ComboBoxTableCell.forTableColumn(filmNames));

        ArrayList<Integer> list = new ArrayList<>();
        for (Theater k : dataLoader.getTheatersList())
            list.add(k.getTheaterNumber());

        ObservableList<Integer> theatersList = FXCollections.observableArrayList(list);
        for (Theater theater : dataLoader.getTheatersList())
            theatersList.add(theater.getTheaterNumber());
        theaterCol.setCellFactory(ComboBoxTableCell.forTableColumn(theatersList));

        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        standartPriceCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setStandartCost(event.getNewValue());
        });

        filmCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setFilmName(event.getNewValue());
        });

        timeCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSessionTime(event.getNewValue());
        });

        theaterCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setTheaterNumber(event.getNewValue());
        });

        dateCol.setOnEditCommit(event -> {
            Date date = event.getNewValue();
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSessionDate(new java.sql.Date(date.getTime()));
        });

        vipPriceCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setVipCost(event.getNewValue());
        });

    }
}
