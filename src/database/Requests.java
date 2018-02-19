package database;



public class Requests {

    /**
     * SELECT
     */
    public static final String GET_ALL_FILMS = "SELECT * FROM Films";
    public static final String GET_SESSIONS_BY_DATE = "SELECT * FROM Sessions WHERE SessionDate = ?";
    public static final String GET_FILM_NAMES = "SELECT Name FROM Films";
    public static final String GET_SCHEDULE = "SELECT * FROM Sessions";
    public static final String GET_POSTERS = "SELECT  Poster FROM Films";
    public static final String GET_THEATERS = "SELECT * FROM Theaters";
    public static final String GET_TICKETS = "SELECT * FROM Tickets";
    public static final String GET_ORDERS = "SELECT * FROM Orders";
    public static final String GET_FILMTIMES_BY_DATE = "SELECT SessionTime FROM Sessions WHERE SessionDate like ? AND FilmName like ?";
    public static final String GET_SESSION_BY_DATETIME_AND_NAME = "SELECT * FROM (Sessions INNER JOIN Theaters on Theaters.TheaterNumber = Sessions.TheaterNumber) WHERE FilmName like ? AND SessionDate like ? AND SessionTime like ?";
    public static final String GET_BOOKED_SEATS = "SELECT SeatNumber, RowNumber FROM Tickets WHERE idSessions = ?";

    /**
     * INSERT
     */
    public static final String ADD_FILM = "INSERT INTO  Poster VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ADD_FILM_WITHOUT_POSTERS = "INSERT INTO  Poster (Name, Length, Country, Description, Genre) VALUES (?, ?, ?, ?, ?)";
    public static final String ADD_THEATER = "INSERT INTO Theaters VALUES (?, ?, ?, ?)";
    public static final String ADD_TICKET = "INSERT INTO Tickets VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ADD_SESSION = "INSERT INTO Sessions VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * UPDATE
     */
    public static final String UPDATE_THEATER = "UPDATE Theaters SET SeatsNumber = ?, SeatsInRow = ?, VIPRow = ? WHERE TheaterNumber = ?";
    public static final String UPDATE_TICKET = "UPDATE Tickets SET idSessions = ?, TicketType = ?, BookID = ?, SeatNumber = ?, RowNumber = ? WHERE idTickets = ?";
    public static final String UPDATE_SCHEDULE = "UPDATE Sessions SET FilmName = ?, TheaterNumber = ?, SessionDate = ?, OrdCost = ?, VipCost = ?, SessionTime = ? WHERE idSessions = ?";

    /**
     * DELETE
     */
    public static final String DELETE_SESSION = "DELETE FROM Sessions Where idSessions = ?";
    public static final String DELETE_THEATER = "DELETE FROM Theaters Where TheaterNumber = ?";
}
