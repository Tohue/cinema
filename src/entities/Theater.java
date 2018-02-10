package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Theater {

    private IntegerProperty theaterNumber;
    private IntegerProperty seatsNumber;
    private IntegerProperty seatsInRow;
    private IntegerProperty VIPRowNumber;


    public Theater(int theaterNumber, int seatsNumber, int seatsInRow, int VIPRowNumber) {
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
        this.seatsNumber = new SimpleIntegerProperty(seatsNumber);
        this.seatsInRow = new SimpleIntegerProperty(seatsInRow);
        this.VIPRowNumber = new SimpleIntegerProperty(VIPRowNumber);
    }

    public int getSeatsInRow() {
        return seatsInRow.get();
    }

    public IntegerProperty seatsInRowProperty() {
        return seatsInRow;
    }

    public void setSeatsInRow(int seatsInRow) {
        this.seatsInRow.set(seatsInRow);
    }

    public int getVIPRowNumber() {
        return VIPRowNumber.get();
    }

    public IntegerProperty VIPRowNumberProperty() {
        return VIPRowNumber;
    }

    public void setVIPRowNumber(int VIPRowNumber) {
        this.VIPRowNumber.set(VIPRowNumber);
    }

    public int getTheaterNumber() {
        return theaterNumber.get();
    }

    public IntegerProperty theaterNumberProperty() {
        return theaterNumber;
    }

    public void setTheaterNumber(int theaterNumber) {
        this.theaterNumber.set(theaterNumber);
    }

    public int getSeatsNumber() {
        return seatsNumber.get();
    }

    public IntegerProperty seatsNumberProperty() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber.set(seatsNumber);
    }
}
