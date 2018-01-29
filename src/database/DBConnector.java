package database;

import config.DBConfig;

import java.sql.*;


/**
 * Подключение к базе данных по переданному URL, логину и паролю
 */
public class DBConnector {

    private static  String URL = "";
    private static  String username = "";
    private static  String password = "";

    private static Connection connection = null;

    public static boolean isConnected() {
        return connection != null;
    }

    public static void setValues(String username, String password) {

        DBConnector.URL = DBConfig.getDATABASEURL();
        DBConnector.username = username;
        DBConnector.password = password;
    }

    public static Connection doConnection() throws Exception {


            Class.forName(DBConfig.getDRIVER());
            connection = DriverManager.getConnection(URL, username, password);
            if (connection == null)
                System.out.println("No connection");
            else   System.out.println("Connected");


        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet sendRequest(String request) throws SQLException {
        ResultSet rs = null;

        if (connection != null) {
            Statement st = connection.createStatement();
            rs = st.executeQuery(request);
        }

        return rs;
    }

}
