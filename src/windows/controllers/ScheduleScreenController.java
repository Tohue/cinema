package windows.controllers;


import database.DataLoader;
import entities.Film;
import entities.Session;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;

public class ScheduleScreenController {

    @FXML
    TableView<Session> scheduleTable;
    @FXML
    TableColumn<Session, String> nameCol;
    @FXML
    TableColumn<Session, Date> dateCol;
    @FXML
    TableColumn<Session, Integer> timeCol;
    @FXML
    TableColumn<Session, Integer> theaterCol;
    @FXML
    TableColumn<Session, Object> buyCol;

    public void initialize() {

        ObservableList<Session> sessionList = null;

        /**
         * Загрузка списка фильмов, если он пустой
         */
        if (DataLoader.getFilmInfoList().isEmpty()) {
            try {
                DataLoader.loadSchedule();
                sessionList = DataLoader.getSessionList();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else sessionList = DataLoader.getSessionList();

        timeCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("sessionTime"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Session, String>("filmName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Session, Date>("sessionDate"));
        theaterCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("theaterNumber"));
        scheduleTable.setItems(sessionList);

    }

}
