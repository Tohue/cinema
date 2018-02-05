package windows.controllers.contentScreens;


import database.DataLoader;
import entities.Film;
import entities.Session;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import windows.controllers.AbstractController;
import windows.windowStarters.MainScreen;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class ScheduleScreenController extends AbstractController {

    @FXML
    TableView<Session> scheduleTable;
    @FXML
    TableColumn<Session, String> nameCol;
    @FXML
    TableColumn<Session, Time> timeCol;
    @FXML
    TableColumn<Session, Integer> theaterCol;
    @FXML
    TableColumn<Session, Integer> standartPriceCol;
    @FXML
    TableColumn<Session, Integer> vipPriceCol;



    public void initialize() {

        ObservableList<Session> sessionList = null;

        /**
         * Загрузка расписания, если оно пустое
         */
        if (DataLoader.getSessionList().isEmpty()) {
            try {
                DataLoader.loadSchedule();
                sessionList = DataLoader.getSessionList();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else sessionList = DataLoader.getSessionList();

        timeCol.setCellValueFactory(new PropertyValueFactory<Session, Time>("sessionTime"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Session, String>("filmName"));
        theaterCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("theaterNumber"));
        standartPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("standartCost"));
        vipPriceCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("vipCost"));

        scheduleTable.setItems(sessionList);

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
}
