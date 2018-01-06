package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import java.sql.Date;


public class Session {
    private StringProperty filmName;
    private ObjectProperty<Date> sessionDate;
    private IntegerProperty sessionTime;
    private IntegerProperty theaterNumber;
    private IntegerProperty idSession;

    public String getFilmName() {
        return filmName.get();
    }

    public StringProperty filmNameProperty() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName.set(filmName);
    }

    public Date getSessionDate() {
        return sessionDate.get();
    }

    public ObjectProperty<Date> sessionDateProperty() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate.set(sessionDate);
    }

    public int getSessionTime() {
        return sessionTime.get();
    }

    public IntegerProperty sessionTimeProperty() {
        return sessionTime;
    }

    public void setSessionTime(int sessionTime) {
        this.sessionTime.set(sessionTime);
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

    public int getIdSession() {
        return idSession.get();
    }

    public IntegerProperty idSessionProperty() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession.set(idSession);
    }
}
