package windows.controllers.indoEditors;

import database.DBConnector;
import database.Requests;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableColumn<Date, Integer> dateCol;
    @FXML
    TableColumn<Order, Integer> costCol;

    ObservableList<Order> orders = FXCollections.observableArrayList();

    public void initialize() {

        setTable();

    }

    private void setTable() {

        try {
            ResultSet resultSet = DBConnector.sendRequest(Requests.GET_ORDERS);

            while (resultSet.next())
                orders.add(new Order(resultSet.getInt("idOrders"), resultSet.getDate("OrderDateTime"), resultSet.getInt("TotalCost")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("number"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Date, Integer>("dateOrder"));
        costCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderCost"));
        orderTable.setItems(orders);

    }

    @Override
    public void saveEditing() {

    }

    @Override
    public void saveCreating() {

    }

    @Override
    public void updateInfo() {

    }

    @Override
    public void creatingError() {

    }

    @Override
    public void editingError() {

    }

    @Override
    public void delete() {

    }
}
