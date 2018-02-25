package windows.components;

import entities.Pair;
import javafx.scene.control.CheckBox;


public class SeatView extends CheckBox {

    private boolean VIP;
    private boolean order;
    private int row;
    private int num;
    private Pair<Integer> coords;

    public Pair<Integer> getCoords() {
        return coords;
    }

    public SeatView(int row, int num) {
        this.row = row;
        this.num = num;
        coords = new Pair<>(row, num);
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
        this.setStyle("-fx-color : red;");
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
        this.setSelected(true);
        this.setDisable(true);
    }

    public int getRow() {
        return row;
    }

    public int getNum() {
        return num;
    }
}
