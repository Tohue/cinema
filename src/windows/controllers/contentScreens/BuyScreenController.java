package windows.controllers.contentScreens;

import entities.Session;
import entities.Ticket;

public class BuyScreenController {

    private static Session session;
    private static Ticket ticket;

    public static void setSession(Session session) {
        BuyScreenController.session = session;
    }

    public static void setTicket(Ticket ticket) {
        BuyScreenController.ticket = ticket;
    }
}
