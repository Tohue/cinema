package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Pair;
import entities.Session;
import entities.Theater;
import entities.Ticket;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import windows.components.SeatView;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class SelectSeatsController extends AbstractController {

    private static Session session;
    private static Theater theater;
    private int ticketSum;
    private int countSelectedSeats;
    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);

    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane infoPane;

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
    @FXML
    AnchorPane seatsAnchorPane;

    public static void setTheater(Theater theater) {
        SelectSeatsController.theater = theater;
    }

    public static void setSession(Session session) {
        SelectSeatsController.session = session;
    }

    public void initialize() {

        setSessionInfo();
        setSeatsView();
        checkSelection();

    }

    private void setSessionInfo() {

            theaterNumLabel.setText(theaterNumLabel.getText() + session.getTheaterNumber());
            filmNameLabel.setText(session.getFilmName());
            timeLabel.setText(session.getSessionTime());

    }

    private void setSeatsView() {

        DataLoader dataLoader = new DataLoader();
        Set<Pair<Integer>> booked = dataLoader.getBookedSeats(session);

        orderedLabel.setText(orderedLabel.getText() +  " " + booked.size());
        freeSeatsCountLabel.setText(freeSeatsCountLabel.getText() + " " + (theater.getSeatsNumber() - booked.size()));

        int seatsInRow = theater.getSeatsInRow();
        int VIPRows = theater.getVIPRowNumber();
        int ordRows = theater.getSeatsNumber() / seatsInRow - VIPRows;
        int allRows = ordRows + VIPRows;

        int seatsCount = 1;
        int XAnchor;
        int YAnchor = 0;

        seatsAnchorPane.prefWidthProperty().bind(mainPane.widthProperty().divide(1.5));
        seatsAnchorPane.prefHeightProperty().bind(mainPane.heightProperty().divide(2))
        ;
        IntegerProperty XLayout = new SimpleIntegerProperty();
        XLayout.bind(seatsAnchorPane.widthProperty().divide(3));

        for (int currRowIndex = 1; currRowIndex <= allRows; currRowIndex++) {
            XAnchor = 0;
            YAnchor += 50;
            for (int currSeatNum = 1; currSeatNum < seatsInRow; currSeatNum++) {

                SeatView seat = new SeatView();

                for (Pair<Integer> pair : booked)
                    if (pair.getElement1() == currRowIndex && pair.getElement2() == currSeatNum)
                        seat.setOrder(true);


                if (currRowIndex > ordRows)
                    seat.setVIP(true);

                seat.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (seat.isSelected()) {
                            if (seat.isVIP())
                                ticketSum += session.getVipCost();
                            else ticketSum += session.getStandartCost();

                            countSelectedSeats++;
                        } else {
                            if (seat.isVIP())
                                ticketSum -= session.getVipCost();
                            else ticketSum -= session.getStandartCost();
                            countSelectedSeats--;
                            checkSelection();
                        }
                        updateFields();
                    }
                });

                AnchorPane.setTopAnchor(seat, YAnchor * 1.0);
                AnchorPane.setLeftAnchor(seat, XAnchor * 1.0);
                seatsAnchorPane.getChildren().add(seat);
                XAnchor += 50;
            }
        }

    }

    private void updateFields() {

        sumLabel.setText(String.valueOf(ticketSum));
        ticketCountLabel.setText(String.valueOf(countSelectedSeats));
        acceptButton.disableProperty().bind(isSelected.not());

    }

    private void checkSelection() {

        if (countSelectedSeats == 0)
            isSelected.setValue(false);

    }

    private void openGoodScreen() {

//        try {
//            ScreenStarter.StartGoodBuyingScreen(null, session, stage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
