package windows.controllers.indoEditors;

import database.DBConnector;
import database.Requests;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import windows.controllers.AbstractController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderChangeScreen extends AbstractController implements infoEditor {

    @FXML
    TableView<Order> orderTable;
    @FXML
    TableColumn<Order, Integer> idCol;
    @FXML
    TableColumn<Order, Date> dateCol;
    @FXML
    TableColumn<Order, Integer> costCol;
    @FXML
    Label successLabel;

    ObservableList<Order> orders = FXCollections.observableArrayList();

    public void initialize() {

        updateInfo();
        setTable();

    }

    private void setTable() {


        idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("number"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("dateOrder"));
        costCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderCost"));
        orderTable.setItems(orders);

        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        costCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


    }

    @Override
    public void saveEditing() {

    }

    @Override
    public void saveCreating() {

    }

    @Override
    public void updateInfo() {

        orders.clear();
        try {
            ResultSet resultSet = DBConnector.sendRequest(Requests.GET_ORDERS);

            while (resultSet.next())
                orders.add(new Order(resultSet.getInt("idOrders"), resultSet.getDate("OrderDateTime"), resultSet.getInt("TotalCost")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void creatingError() {

    }

    @Override
    public void editingError() {

    }

    @Override
    public void delete() {

        if (orderTable.getSelectionModel().getSelectedItems() != null) {

            try {

                int orderID =  orderTable.getSelectionModel().getSelectedItem().getNumber();

                DBConnector.getConnection().setAutoCommit(false);
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_ORDER);
                statement.setInt(1,orderID);
                statement.executeUpdate();

                PreparedStatement ticket_statement = DBConnector.getConnection().prepareStatement(Requests.DELETE_TICKETS_BY_ORDER);
                ticket_statement.setInt(1, orderID);
                ticket_statement.executeUpdate();
                updateInfo();
                successLabel.setVisible(true);

            } catch (SQLException e) {
                successLabel.setVisible(false);
                e.printStackTrace();
            } finally {
                try {
                    DBConnector.getConnection().commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    DBConnector.getConnection().setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
