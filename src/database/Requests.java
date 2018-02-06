package database;



public class Requests {

    /**
     * SELECT
     */
    public static final String GET_ALL_FILMS = "SELECT * FROM Films";
    public static final String GET_SESSIONS_BY_DATE = "SELECT * FROM Sessions WHERE SessionDate = ?";
    public static final String GET_SCHEDULE = "SELECT * FROM Sessions";
    public static final String GET_POSTERS = "SELECT  Poster FROM Films";
    public static final String GET_THEATERS = "SELECT * FROM Theaters";
    public static final String GET_TICKETS = "SELECT * FROM Tickets";
    public static final String GET_ORDERS = "SELECT * FROM Orders";

    /**
     * INSERT
     */
    public static final String ADD_FILM = "INSERT INTO  Poster VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ADD_FILM_WITHOUT_POSTERS = "INSERT INTO  Poster (Name, Length, Country, Description, Genre) VALUES (?, ?, ?, ?, ?)";
    public static final String ADD_THEATER = "INSERT INTO Theaters VALUES (?, ?)";
    public static final String ADD_TICKET = "INSERT INTO Tickets VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * UPDATE
     */
    public static final String UPDATE_THEATER = "UPDATE Theaters SET SeatsNumber = ? WHERE TheaterNumber = ?";
    public static final String UPDATE_TICKET = "UPDATE Tickets SET idSessions = ?, TicketType = ?, BookID = ?, SeatNumber = ?, RowNumber = ? WHERE idTickets = ?";

}
