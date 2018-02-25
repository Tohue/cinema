package windows.controllers.contentScreens;

import database.DBConnector;
import database.DataLoader;
import database.Requests;

import entities.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windows.components.SeatView;
import windows.controllers.AbstractController;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class BuyScreenController extends AbstractController {

    @FXML
    Label titleLabel;
    @FXML
    Label filmNameLabel;
    @FXML
    Label filmTimeLabel;
    @FXML
    Label countOrdLabel;
    @FXML
    Label countVIPLabel;
    @FXML
    Label sumLabel;
    @FXML
    Label theaterLabel;
    @FXML
    SubScene subScene;
    @FXML
    AnchorPane mainPane;

    private static Session session;
    private static int ordTicketNum;
    private static int vipTicketNum;
    private static int sum;
    private static ArrayList<SeatView> seatsList;

    public static void setSeatsCoordsList(ArrayList<SeatView> seatsCoordsList) {
        BuyScreenController.seatsList = seatsCoordsList;
    }

    public static void setSum(int sum) {
        BuyScreenController.sum = sum;
    }

    public static void setOrdTicketNum(int ordTicketNum) {
        BuyScreenController.ordTicketNum = ordTicketNum;
    }

    public static void setVipTicketNum(int vipTicketNum) {
        BuyScreenController.vipTicketNum = vipTicketNum;
    }

    public static void setSession(Session session) {
        BuyScreenController.session = session;
    }

    public void initialize() {


        subScene.heightProperty().bind(mainPane.heightProperty());
        subScene.widthProperty().bind(mainPane.widthProperty());
        filmNameLabel.setText(filmNameLabel.getText() + " " + session.getFilmName());
        filmTimeLabel.setText(filmTimeLabel.getText() + " " + session.getSessionTime());
        theaterLabel.setText(theaterLabel.getText() + " " + session.getTheaterNumber());
        countOrdLabel.setText(countOrdLabel.getText() + " " + ordTicketNum);
        countVIPLabel.setText(countVIPLabel.getText() + " " + vipTicketNum);
        sumLabel.setText(sumLabel.getText() + " " + sum);

    }

    public void buy() {


            Connection connection = DBConnector.getConnection();

            try {

                connection.setAutoCommit(false);
                PreparedStatement orderStatement = connection.prepareStatement(Requests.ADD_ORDER);
                orderStatement.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
                orderStatement.setInt(2, sum);
                orderStatement.executeUpdate();

                Integer lastOrder = new DataLoader().getLastOrderId();
                System.out.println(lastOrder);

                for (SeatView seat : seatsList) {
                    PreparedStatement ticketStatement = connection.prepareStatement(Requests.ADD_TICKET);
                    ticketStatement.setInt(1, session.getIdSession());
                    ticketStatement.setString(2, (seat.isVIP() ? "VIP" : "ORD"));
                    ticketStatement.setInt(3, lastOrder.intValue());
                    ticketStatement.setInt(4, seat.getNum());
                    ticketStatement.setInt(5, seat.getRow());
                    ticketStatement.executeUpdate();
                    connection.commit();
                }

                if (orderStatement != null)
                    orderStatement.close();



                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        try {
            backToMainScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void backToMainScreen() throws IOException {

        stage.close();
        getLastWindow().close();


    }

}
