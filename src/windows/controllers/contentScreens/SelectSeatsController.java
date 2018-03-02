package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Pair;
import entities.Session;
import entities.Theater;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import windows.components.SeatView;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


public class SelectSeatsController extends AbstractController {

    private static Session session;
    private static Theater theater;
    private int ticketSum = 0;
    private int countSelectedSeats = 0;
    private int vipNum = 0;
    private int ordNum = 0;
    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
    private ArrayList<SeatView> seatsList = new ArrayList<>();

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

        acceptButton.disableProperty().bind(isSelected.not());
        setSessionInfo();
        setSeatsView();
        checkSelection();
        updateFields();

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

        int XAnchor;
        int YAnchor = 0;

        seatsAnchorPane.prefWidthProperty().bind(mainPane.widthProperty().divide(1.5));
        seatsAnchorPane.prefHeightProperty().bind(mainPane.heightProperty().divide(2));

        IntegerProperty XLayout = new SimpleIntegerProperty();
        XLayout.bind(seatsAnchorPane.widthProperty().divide(3));

        for (int currRowIndex = 1; currRowIndex <= allRows; currRowIndex++) {
            XAnchor = 0;
            YAnchor += 50;
            for (int currSeatNum = 1; currSeatNum < seatsInRow; currSeatNum++) {

                SeatView seat = new SeatView(currRowIndex, currSeatNum);

                for (Pair<Integer> pair : booked)
                    if (pair.getElement1() == currRowIndex && pair.getElement2() == currSeatNum)
                        seat.setOrder(true);


                if (currRowIndex > ordRows)
                    seat.setVIP(true);


                seat.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (seat.isSelected()) {
                            if (seat.isVIP()) {
                                ticketSum += session.getVipCost();
                                vipNum++;
                                seatsList.add(seat);
                            }
                            else {
                                ticketSum += session.getStandartCost();
                                ordNum++;
                                seatsList.add(seat);
                            }

                            countSelectedSeats++;
                        } else {
                            if (seat.isVIP()) {
                                ticketSum -= session.getVipCost();
                                vipNum--;
                               seatsList.remove(seat);
                            }
                            else {
                                ticketSum -= session.getStandartCost();
                                ordNum--;
                                seatsList.remove(seat);
                            }
                            countSelectedSeats--;

                        }
                        checkSelection();
                        updateFields();
                    }
                });

                AnchorPane.setTopAnchor(seat, YAnchor * 1.0);
                AnchorPane.setLeftAnchor(seat, XAnchor * 1.0);
                seatsAnchorPane.getChildren().add(seat);
                XAnchor += 40;
            }
        }

    }

    private void updateFields() {

        sumLabel.setText(String.valueOf(ticketSum));
        ticketCountLabel.setText(String.valueOf(countSelectedSeats));


    }

    private void checkSelection() {

        if (countSelectedSeats == 0)
            isSelected.setValue(false);
        if (!isSelected.get() && countSelectedSeats > 0)
            isSelected.setValue(true);

    }


    public void openGoodScreen() {

        try {
            ScreenStarter.StartGoodBuyingScreen(session, ordNum, vipNum, ticketSum, seatsList, stage);
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
