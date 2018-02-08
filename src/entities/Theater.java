package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Theater {

    private IntegerProperty theaterNumber;
    private IntegerProperty seatsNumber;


    public Theater(int theaterNumber, int seatsNumber) {
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
        this.seatsNumber = new SimpleIntegerProperty(seatsNumber);
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
