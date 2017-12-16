package config;

public class DBConfig {

    private static final String HOSTNAME = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static String getHOSTNAME() {
        return HOSTNAME;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getDRIVER() {
        return DRIVER;
    }
}
