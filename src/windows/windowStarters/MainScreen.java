package windows.windowStarters;

import config.DBPropertiesParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import windows.builders.FullSizeWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import windows.builders.ModalityBuilder;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainScreen extends Application {

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

        primaryStage = FullSizeWindowBuilder.getFullSizeScreen("mainScreen.fxml");
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
