package windows.windowStarters;

import config.DBPropertiesParser;
import database.DBConnector;
import windows.builders.FullSizeWindowBuilder;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;

public class MainScreen {

    public void openMainScreen() throws Exception{

        Stage primaryStage;

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

    }


   // public static void main(String[] args) {
  //      launch(args);
  //  }
}
