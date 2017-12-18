package windows.windowStarters;

import config.DBConfig;
import config.DBPropertiesParser;
import database.DBConnector;
import windows.builders.FullSizeWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;

public class MainScreen extends Application {

    volatile int a = 20;
    @Override
    public void start(Stage primaryStage) throws Exception{

        File DBConfig =  new File("./resources/configFiles/DBConfig.xml");
        DBPropertiesParser dbPropertiesParser = new DBPropertiesParser();
        if (!DBConfig.exists()) {
            System.out.println("не парсим");
            dbPropertiesParser.newDBProperties();
        }
        else {
            System.out.println("будем парсить");
            dbPropertiesParser.parseBDProperties();
        }

        primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("mainScreen.fxml");
        primaryStage.show();

        DBConnector dbConnector = new DBConnector(config.DBConfig.getDATABASEURL(), "a218696_sarik", "11111111");
        Connection connection = dbConnector.connectToDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
