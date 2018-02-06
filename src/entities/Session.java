package entities;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Сеанс
 */
public class Session {
    private StringProperty filmName;
    private ObjectProperty<Date> sessionDate;
    private StringProperty sessionTime;
    private IntegerProperty theaterNumber;
    private IntegerProperty idSession;
    private IntegerProperty standartCost;
    private IntegerProperty vipCost;

    public Session(String filmName, int theaterNumber) {
        this.filmName = new SimpleStringProperty(filmName);
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
    }

    public Session(String filmName, Date sessionDate, Time sessionTime, int theaterNumber, int idSession, int standartCost, int vipCost) {
        this.filmName = new SimpleStringProperty(filmName);
        DateFormat format = new SimpleDateFormat("kk : mm");
        this.sessionDate = new SimpleObjectProperty<Date>(sessionDate);
        this.sessionTime = new SimpleStringProperty(format.format(sessionTime));
        this.theaterNumber = new SimpleIntegerProperty(theaterNumber);
        this.idSession = new SimpleIntegerProperty(idSession);
        this.standartCost = new SimpleIntegerProperty(standartCost);
        this.vipCost = new SimpleIntegerProperty(vipCost);
    }

    public int getStandartCost() {
        return standartCost.get();
    }

    public IntegerProperty standartCostProperty() {
        return standartCost;
    }

    public void setStandartCost(int standartCost) {
        this.standartCost.set(standartCost);
    }

    public int getVipCost() {
        return vipCost.get();
    }

    public IntegerProperty vipCostProperty() {
        return vipCost;
    }

    public void setVipCost(int vipCost) {
        this.vipCost.set(vipCost);
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

    public String getSessionTime() {
        return sessionTime.get();
    }

    public StringProperty sessionTimeProperty() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
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
