package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Session;
import entities.Ticket;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TicketChangeController extends AbstractController implements infoEditor {

    //Таблица
    @FXML
    TableView ticketInfoTable;

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
        int i = 0;
        boolean errorHappened = false;
        while (iterator.hasNext()) {

            int ticketNum = iterator.next();

            try {

                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.UPDATE_TICKET);
                statement.setInt(1, ((Ticket) ticketInfoTable.getItems().get(i)).getIdSession());
                statement.setString(2, ((Ticket) ticketInfoTable.getItems().get(i)).getTicketType());
                statement.setInt(3, ((Ticket) ticketInfoTable.getItems().get(i)).getBookID());
                statement.setInt(4, ((Ticket) ticketInfoTable.getItems().get(i)).getSeatsNumber());
                statement.setInt(5, ((Ticket) ticketInfoTable.getItems().get(i)).getRowNumber());
                statement.setInt(6, ticketNum);
                statement.executeUpdate();

            } catch (SQLException e) {

                errorHappened = true;
                e.printStackTrace();
            }

            i++;
        }

        if (!errorHappened)
            hideEditError();
        else editingError();

        updateInfo();
    }

    @Override
    public void saveCreating()   {

        if (DBConnector.isConnected()) {
            try {

            PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.ADD_TICKET);
            statement.setInt(1, Integer.parseInt(addNumTickField.getText()));
            statement.setInt(2, getFilmsNumFromString(addNumSessField.getValue()));
            statement.setString(3, addTickTypeField.getValue());
            statement.setInt(4, addNumOrdField.getValue());
            statement.setInt(5, Integer.parseInt(addNumSeatsField.getText()));
            statement.setInt(6, Integer.parseInt(addNumRowField.getText()));
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
            DataLoader.loadTickets();
            observableList = DataLoader.getTicketList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Инициализация колонок таблицы
         */
        tickNumCol.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("idTicket"));
        ordNumCol.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("bookID"));
        sessNumCol.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("idSession"));
        tickTypeCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ticketType"));
        rowNumCol.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("rowNumber"));
        seatsNumCol.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("seatsNumber"));
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

    public void initialize() {

        tickTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tickTypeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, String> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTicketType(event.getNewValue());
            }
        });

        sessNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sessNumCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, Integer> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setIdSession(event.getNewValue());
            }
        });

        ordNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ordNumCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, Integer> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setBookID(event.getNewValue());
            }
        });

        rowNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rowNumCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, Integer> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setRowNumber(event.getNewValue());
            }
        });

        seatsNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        seatsNumCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, Integer> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setSeatsNumber(event.getNewValue());
            }
        });

        updateLists();
        updateInfo();

    }

    @Override
    public void closeThisFuckinWindow() {
        try {
            ScreenStarter.Start("menu/AdminWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.closeThisFuckinWindow();
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

            DataLoader.loadSchedule();
            ObservableList<Session> sessions = DataLoader.getSessionList();
            int sessionsID;
            String sessionName;
            for (int i = 0; i < sessions.size(); i++) {

                sessionsID = sessions.get(i).getIdSession();
                sessionName = sessions.get(i).getFilmName();
                addNumSessField.getItems().add(sessionsID + " (" + sessionName + " " + sessions.get(i).getSessionDate() + " " +  sessions.get(i).getSessionTime() + ")");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DataLoader.loadOrders();
            addNumOrdField.setItems(DataLoader.getOrderNumsList());
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
