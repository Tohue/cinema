package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

/**
 * Заказ
 */
public class Order {

    private IntegerProperty number;
    private ObjectProperty<Date> dateOrder;
    private IntegerProperty orderCost;

    public Order(int number, Date dateOrder, int orderCost) {
        this.number = new SimpleIntegerProperty(number);
        this.dateOrder = new SimpleObjectProperty<>(dateOrder);
        this.orderCost = new SimpleIntegerProperty(orderCost);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public Date getDateOrder() {
        return dateOrder.get();
    }

    public ObjectProperty<Date> dateOrderProperty() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder.set(dateOrder);
    }

    public int getOrderCost() {
        return orderCost.get();
    }

    public IntegerProperty orderCostProperty() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost.set(orderCost);
    }
}
