package windows.components;

import javafx.scene.control.CheckBox;

public class SeatView extends CheckBox {

    private boolean VIP;
    private boolean order;


    public SeatView() {

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
}
