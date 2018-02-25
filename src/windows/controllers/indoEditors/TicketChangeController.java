package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Session;
import entities.Ticket;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

public class TicketChangeController extends AbstractController implements infoEditor {

    //Таблица
    @FXML
    TableView<Ticket> ticketInfoTable;

    @FXML
    TableColumn<Ticket, Integer> tickNumCol;
    @FXML
    TableColumn<Ticket, Integer> sessNumCol;
    @FXML
    TableColumn<Ticket, String> tickTypeCol;
    @FXML
    TableColumn<Ticket, Integer> ordNumCol;
    @FXML
    TableColumn<Ticket, Integer> rowNumCol;
    @FXML
    TableColumn<Ticket, Integer> seatsNumCol;

    // Поля ввода
    @FXML
    TextField  addNumTickField;
    @FXML
    ChoiceBox<String> addNumSessField;
    @FXML
    ComboBox<String> addTickTypeField;
    @FXML
    ChoiceBox<Integer> addNumOrdField;
    @FXML
    TextField addNumRowField;
    @FXML
    TextField addNumSeatsField;

    // Лэйблы с ошибками
    @FXML
    Label errorEditLabel;
    @FXML
    Label errorAddLabel1;
    @FXML
    Label errorAddLabel2;


    private HashSet<Integer> changedRows = new HashSet<>();


    @Override
    public void saveEditing() {

        Iterator<Integer> iterator = changedRows.iterator();
        boolean errorHappened = false;
        while (iterator.hasNext()) {

            int id = iterator.next();

            try {

                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.UPDATE_TICKET);
                statement.setInt(1, ((Ticket) ticketInfoTable.getItems().get(id)).getIdSession());
                statement.setString(2, ((Ticket) ticketInfoTable.getItems().get(id)).getTicketType());
                statement.setInt(3, ((Ticket) ticketInfoTable.getItems().get(id)).getBookID());
                statement.setInt(4, ((Ticket) ticketInfoTable.getItems().get(id)).getSeatsNumber());
                statement.setInt(5, ((Ticket) ticketInfoTable.getItems().get(id)).getRowNumber());
                statement.setInt(6, ((Ticket) ticketInfoTable.getItems().get(id)).getIdTicket());
                statement.executeUpdate();

            } catch (SQLException e) {

                errorHappened = true;
                e.printStackTrace();
            }

        }

        if (!errorHappened)
            hideEditError();
        else editingError();

        updateInfo();
        changedRows.clear();
    }

    @Override
    public void saveCreating()   {

        if (DBConnector.isConnected()) {
            try {

            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_TICKET);
            statement.setInt(1, getFilmsNumFromString(addNumSessField.getValue()));
            statement.setString(2, addTickTypeField.getValue());
            statement.setInt(3, addNumOrdField.getValue());
            statement.setInt(4, Integer.parseInt(addNumSeatsField.getText()));
            statement.setInt(5, Integer.parseInt(addNumRowField.getText()));
            statement.executeUpdate();
            hideSaveError();
            } catch (SQLException e) {
                viewSaveError();
                e.printStackTrace();
            }
        }
        updateInfo();
    }

    @Override
    public void updateInfo() {

        ObservableList<Ticket> observableList = null;
        ticketInfoTable.getItems().clear();

        /**
         * Загрузка списка фильмов
         */
        try {
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadTickets();
            observableList = dataLoader.getTicketList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Инициализация колонок таблицы
         */
        tickNumCol.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        ordNumCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        sessNumCol.setCellValueFactory(new PropertyValueFactory<>("idSession"));
        tickTypeCol.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        rowNumCol.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        seatsNumCol.setCellValueFactory(new PropertyValueFactory<>("seatsNumber"));
        ticketInfoTable.setItems(observableList);
    }

    @Override
    public void creatingError() {
        viewSaveError();
    }

    @Override
    public void editingError() {
        viewEditError();
    }

    @Override
    public void delete() {

        if (ticketInfoTable.getSelectionModel().getSelectedItems() != null && ticketInfoTable.getSelectionModel().getSelectedItems().size() > 0) {
            try {
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_TICKET);
                statement.setInt(1, ticketInfoTable.getSelectionModel().getSelectedItem().getIdTicket());
                statement.executeUpdate();
                updateInfo();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void initialize() {

        tickTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tickTypeCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setTicketType(event.getNewValue());
        });

        sessNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sessNumCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setIdSession(event.getNewValue());
        });

        ordNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ordNumCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setBookID(event.getNewValue());
        });

        rowNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rowNumCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setRowNumber(event.getNewValue());
        });

        seatsNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        seatsNumCol.setOnEditCommit(event -> {
            changedRows.add(event.getTablePosition().getRow());
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setSeatsNumber(event.getNewValue());
        });

        updateLists();
        updateInfo();

    }


    private void viewEditError() {
        errorEditLabel.setVisible(true);
    }

    private void hideEditError() {
        errorEditLabel.setVisible(false);
    }

    private void viewSaveError() {
        errorAddLabel1.setVisible(true);
        errorAddLabel2.setVisible(true);
    }

    private void hideSaveError() {
        errorAddLabel1.setVisible(false);
        errorAddLabel2.setVisible(false);
    }

    private void updateLists() {

        addTickTypeField.getItems().clear();
        addNumOrdField.getItems().clear();
        addNumSessField.getItems().clear();

        addTickTypeField.getItems().add("ORD");
        addTickTypeField.getItems().add("VIP");

        try {
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadSchedule();
            ObservableList<Session> sessions = dataLoader.getSessionList();
            int sessionsID;
            String sessionName;
            for (Session session : sessions) {

                sessionsID = session.getIdSession();
                sessionName = session.getFilmName();
                addNumSessField.getItems().add(sessionsID + " (" + sessionName + " " + session.getSessionDate() + " " + session.getSessionTime() + ")");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadOrders();
            addNumOrdField.setItems(dataLoader.getOrderNumsList());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getFilmsNumFromString(String string) {

        int index = string.indexOf(" ");
        System.out.println(string.substring(0, index - 1));
        return  Integer.parseInt(string.substring(0, index ));
    }

}
