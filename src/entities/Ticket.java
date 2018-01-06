package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private IntegerProperty bookID;
    private IntegerProperty idSession;
    private IntegerProperty idTicket;
    private StringProperty ticketType;


    public Ticket(int bookID, int idSession, int idTicket, String ticketType) {
        this.bookID = new SimpleIntegerProperty(bookID);
        this.idSession = new SimpleIntegerProperty(idSession);
        this.idTicket = new SimpleIntegerProperty(idTicket);
        this.ticketType = new SimpleStringProperty(ticketType);
    }

    public int getBookID() {
        return bookID.get();
    }

    public IntegerProperty bookIDProperty() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID.set(bookID);
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

    public int getIdTicket() {
        return idTicket.get();
    }

    public IntegerProperty idTicketProperty() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket.set(idTicket);
    }

    public String getTicketType() {
        return ticketType.get();
    }

    public StringProperty ticketTypeProperty() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType.set(ticketType);
    }
}
