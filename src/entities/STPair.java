package entities;

public class STPair {

    private Theater theater;
    private Session session;

    public STPair(Theater theater, Session session) {
        this.theater = theater;
        this.session = session;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
