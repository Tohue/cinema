package config;

public class DBConfig {

    private static String HOSTNAME = "127.0.0.1";
    private static String PORT = "3306";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String DATABASEURL = "jdbc:mysql://localhost:3306/a218696_cinema";

    public static String getDATABASEURL() {
        return DATABASEURL;
    }

    public static void setDATABASEURL(String DATABASEURL) {
        DBConfig.DATABASEURL = DATABASEURL;
    }

    public static String getHOSTNAME() {
        return HOSTNAME;
    }

    public static void setHOSTNAME(String HOSTNAME) {
        DBConfig.HOSTNAME = HOSTNAME;
    }

    public static String getPORT() {
        return PORT;
    }

    public static void setPORT(String PORT) {
        DBConfig.PORT = PORT;
    }

    public static String getDRIVER() {
        return DRIVER;
    }

    public static void setDRIVER(String DRIVER) {
        DBConfig.DRIVER = DRIVER;
    }
}
