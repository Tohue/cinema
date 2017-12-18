package database;

import config.DBConfig;

import java.sql.*;


/**
 * Подключение к базе данных по переданному URL, логину и паролю
 */
public class DBConnector {

    private final String URL;
    private final String username;
    private final String password;

    private Connection connection = null;
    private Statement statement = null;

    public DBConnector(String URL, String login, String password) {
        this.URL = URL;
        this.username = login;
        this.password = password;
    }

    public DBConnector(String username, String password) {

        this.URL = DBConfig.getDATABASEURL();
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws Exception {


            Class.forName(DBConfig.getDRIVER());
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connected");


        return connection;
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
