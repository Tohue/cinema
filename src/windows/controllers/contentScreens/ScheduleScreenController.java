package windows.controllers.contentScreens;


import database.DataLoader;
import entities.Film;
import entities.Session;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import windows.controllers.AbstractController;
import windows.windowStarters.MainScreen;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        todayLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFilmsByDate(LocalDate.now());
            }
        });

        tomorrowLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFilmsByDate(LocalDate.now().plusDays(1));
            }
        });

        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setFilmsByDate(datePicker.getValue());

            }
        });


    }

    @Override
    public void closeThisFuckinWindow() {
        try {
            ScreenStarter.Start("ContentScreens/MainScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.closeThisFuckinWindow();
    }

    private void setFilmsByDate(LocalDate date) {

        ObservableList<Session> sessionList = null;
        try {


            datePicker.setValue(date);
            Calendar calendar = Calendar.getInstance();
            calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

            sessionList = DataLoader.getSessionsByDate(new Date(calendar.getTime().getTime()));
            timeCol.setCellValueFactory(new PropertyValueFactory<Session, String>("sessionTime"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Session, String>("filmName"));
            theaterCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("theaterNumber"));
            standartPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("standartCost"));
            vipPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("vipCost"));
            System.out.println(sessionList.size());
            scheduleTable.setItems(sessionList);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
