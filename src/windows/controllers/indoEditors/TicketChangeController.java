package windows.controllers.indoEditors;

import database.DBConnector;
import database.DataLoader;
import database.Requests;
import entities.Ticket;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

    // Поля ввода
    @FXML
    TextField  addNumTickField;
    @FXML
    TextField addNumSessField;
    @FXML
    TextField addTickTypeField;
    @FXML
    TextField addNumOrdField;

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
                statement.setInt(4, ticketNum);
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
            statement.setInt(2, Integer.parseInt(addNumSessField.getText()));
            statement.setString(3, addTickTypeField.getText());
            statement.setInt(4, Integer.parseInt(addNumOrdField.getText()));
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
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTicketType(event.getNewValue().toString());
            }
        });

        ordNumCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sessNumCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ticket, Integer> event) {
                changedRows.add(event.getTableView().getItems().get(event.getTablePosition().getRow()).getIdTicket());
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTicketType(event.getNewValue().toString());
            }
        });

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

}
