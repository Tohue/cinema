package windows.controllers.indoEditors;


import database.DataLoader;
import entities.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;

import java.sql.SQLException;
import java.util.Date;

public class SessionChangeController extends AbstractController implements infoEditor {

    // Элементы управления
    @FXML
    Spinner<Integer> numSpin;
    @FXML
    Spinner<Integer> theaterSpin;
    @FXML
    ChoiceBox<Integer> filmsField;
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

    ObservableList<Session> sessionsInfoList = null;

    public void initialize() {

        setSliders();
        numberCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("idSession"));
        filmCol.setCellValueFactory(new PropertyValueFactory<Session, String>("filmName"));
        theaterCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("theaterNumber"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Session, Date>("sessionDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Session, String>("sessionTime"));
        standartPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("standartCost"));
        vipPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("vipCost"));
        numberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        updateInfo();


    }

    @Override
    public void saveEditing() {

    }

    @Override
    public void saveCreating() {

    }

    @Override
    public void updateInfo() {


        sessionTable.getItems().clear();

        try {
            DataLoader.loadSchedule();
            sessionsInfoList = DataLoader.getSessionList();
        } catch (SQLException e) {
            e.printStackTrace();
            if (DataLoader.getSessionList() != null)
                sessionsInfoList = DataLoader.getSessionList();
        }

        sessionTable.setItems(sessionsInfoList);

    }

    @Override
    public void creatingError() {

    }

    @Override
    public void editingError() {

    }


    private void setSliders() {

        numSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(7, 1000));
        hourSlider.setShowTickLabels(true);
        hourSlider.setShowTickMarks(true);
        hourSlider.setSnapToTicks(true);
        hourSlider.setMin(0);
        hourSlider.setMax(24);
        hourSlider.setMajorTickUnit(12);
        hourSlider.setMinorTickCount(6);

        minuteSlider.setShowTickLabels(true);
        minuteSlider.setShowTickMarks(true);
        minuteSlider.setMin(0);
        minuteSlider.setMax(60);
        minuteSlider.setMajorTickUnit(30);
        minuteSlider.setMinorTickCount(2);


        minuteSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                minuteLabel.setText(String.valueOf(Math.round(minuteSlider.getValue())));
            }
        });

        hourSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hourLabel.setText(String.valueOf(Math.round(hourSlider.getValue())));
            }
        });
    }
}
