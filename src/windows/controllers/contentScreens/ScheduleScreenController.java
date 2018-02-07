package windows.controllers.contentScreens;


import database.DataLoader;
import entities.Session;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ScheduleScreenController extends AbstractController {

    @FXML
    TableView<Session> scheduleTable;
    @FXML
    TableColumn<Session, String> nameCol;
    @FXML
    TableColumn<Session, String> timeCol;
    @FXML
    TableColumn<Session, Integer> theaterCol;
    @FXML
    TableColumn<Session, Integer> standartPriceCol;
    @FXML
    TableColumn<Session, Integer> vipPriceCol;

    @FXML
    DatePicker datePicker;
    @FXML
    Hyperlink todayLink;
    @FXML
    Hyperlink tomorrowLink;



    public void initialize() {


        setFilmsByDate(LocalDate.now());

        todayLink.setOnAction(event -> setFilmsByDate(LocalDate.now()));

        tomorrowLink.setOnAction(event -> setFilmsByDate(LocalDate.now().plusDays(1)));

        datePicker.setOnAction(event -> setFilmsByDate(datePicker.getValue()));


    }


    private void setFilmsByDate(LocalDate date) {

        ObservableList<Session> sessionList = null;
        try {


            datePicker.setValue(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            sessionList = DataLoader.getSessionsByDate(new Date(calendar.getTime().getTime()));
            timeCol.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("filmName"));
            theaterCol.setCellValueFactory(new PropertyValueFactory<>("theaterNumber"));
            standartPriceCol.setCellValueFactory(new PropertyValueFactory<>("standartCost"));
            vipPriceCol.setCellValueFactory(new PropertyValueFactory<>("vipCost"));
            System.out.println(sessionList.size());
            scheduleTable.setItems(sessionList);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
