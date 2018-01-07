package observableLists;

import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketObsList {
    ObservableList<Ticket> ticketsList = FXCollections.observableArrayList();

    public void clear() {
        ticketsList.clear();
    }

    public void add(Ticket film) {
        ticketsList.add(film);
    }

    public void remove(Ticket film) {
        ticketsList.remove(film);
    }

    public ObservableList<Ticket> getFilmList() {
        return ticketsList;
    }
}
