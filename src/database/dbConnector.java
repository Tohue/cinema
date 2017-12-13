package database;

import java.sql.*;

/**
 * Подключение к базе данных по переданному URL, логину и паролю
 */
class dbConnector {

    private final String URL;
    private final String username;
    private final String password;
    private Driver jdbcDriver;

    private Driver mySqlDriver;
    private Connection connection = null;
    private Statement statement = null;

    public dbConnector(String URL, String login, String password) {
        this.URL = URL;
        this.username = login;
        this.password = password;
    }

    public void connectToDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
