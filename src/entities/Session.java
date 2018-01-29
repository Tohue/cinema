package entities;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Time;


public class Session {
    private StringProperty filmName;
    private ObjectProperty<Date> sessionDate;
    private ObjectProperty<Time> sessionTime;
    private IntegerProperty theaterNumber;
    private IntegerProperty idSession;

    public Session(String filmName, int theaterNumber) {
        this.filmName = new SimpleStringProperty(filmName);
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
    }

    public Session(String filmName, Date sessionDate, int sessionTime, int theaterNumber, int idSession) {
        this.filmName = new SimpleStringProperty(filmName);
        this.sessionDate = new SimpleObjectProperty<Date>(sessionDate);
        this.sessionTime = new SimpleObjectProperty<Time>(new Time(sessionDate.getTime()));
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
        this.idSession = new SimpleIntegerProperty(idSession);
    }

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

    public Time getSessionTime() {
        return sessionTime.get();
    }

    public ObjectProperty<Time> sessionTimeProperty() {
        return sessionTime;
    }

    public void setSessionTime(Time sessionTime) {
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
