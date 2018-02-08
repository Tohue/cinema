package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Pair;
import entities.Session;
import entities.Theater;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import windows.controllers.AbstractController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class SelectSeatsController extends AbstractController {

    private static Session session;
    private static Theater theater;
    private final int seatsInRow = 10;

    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane infoPane;
    @FXML
    GridPane seatsPane;
    @FXML
    Label theaterNumLabel;
    @FXML
    Label filmNameLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label freeSeatsCountLabel;
    @FXML
    Label ticketCountLabel;
    @FXML
    Label sumLabel;
    @FXML
    Button acceptButton;
    @FXML
    Label orderedLabel;

    public static void setTheater(Theater theater) {
        SelectSeatsController.theater = theater;
    }

    public static void setSession(Session session) {
        SelectSeatsController.session = session;
    }

    public void initialize() {

        setSessionInfo();
        setSeatsView();

    }

    private void setSessionInfo() {

            theaterNumLabel.setText(theaterNumLabel.getText() + session.getTheaterNumber());
            filmNameLabel.setText(session.getFilmName());
            timeLabel.setText(session.getSessionTime());

    }

    private void setSeatsView() {

        Set<Pair<Integer>> booked = DataLoader.getBookedSeats(session);
        orderedLabel.setText(orderedLabel.getText() +  " " + booked.size());

        int seatsCount = 1;
        int row = 0;
        int XMargin = 100;
        int YMargin = 80;

        seatsPane.getColumnConstraints().get(0).setPercentWidth(100 / seatsInRow);
        while (seatsCount <= theater.getSeatsNumber()) {


            for (int i = 0; i < seatsInRow; i++) {

                CheckBox seat = new CheckBox();
                seat.setPrefHeight(30);
                seat.setPrefWidth(30);
             //   seat.setPadding(new Insets(5, 30, 5, 30));

                for (Pair<Integer> pair : booked)
                    if (pair.getElement1() == row + 1 && pair.getElement2() == i + 1) {
                        seat.setSelected(true);
                        seat.setDisable(true);
                    }

                seatsPane.addRow(row, seat);
                for (ColumnConstraints constraints : seatsPane.getColumnConstraints())
                    constraints.setPercentWidth(100 / seatsInRow);
                seatsCount++;
            }
            row++;
        }

    }

}
