package database;

import java.sql.Blob;

public class Requests {

    /**
     * SELECT
     */
    public static final String GET_ALL_FILMS = "SELECT * FROM Films";
    public static final String GET_FILM_NAMES = "SELECT Films.Name FROM Films";
    public static final String GET_SCHEDULE = "SELECT * FROM Sessions";
    public static final String GET_POSTERS = "SELECT  Poster FROM Films";
    public static final String GET_THEATERS = "SELECT * FROM Theaters";
    public static final String GET_TICKETS = "SELECT * FROM Tickets";

    /**
     * INSERT
     */
    public static final String ADD_FILM = "INSERT INTO  Poster VALUES (?, ?, ?, ?, ?, ?)";
    public static final String ADD_FILM_WITHOUT_POSTERS = "INSERT INTO  Poster (Name, Length, Country, Description, Genre) VALUES (?, ?, ?, ?, ?)";
    public static final String ADD_THEATER = "INSERT INTO Theaters VALUES (?, ?)";
    public static final String ADD_TICKET = "INSERT INTO Tickets VALUES (?, ?, ?, ?)";

    /**
     * UPDATE
     */
    public static final String UPDATE_THEATER = "UPDATE Theaters SET SeatsNumber = ? WHERE TheaterNumber = ?";
    public static final String UPDATE_TICKET = "UPDATE Tickets SET idSessions = ?, TicketType = ?, BookID = ?, WHERE idTickets = ?";

}
