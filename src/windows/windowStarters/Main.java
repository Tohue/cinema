package windows.windowStarters;

import config.DBPropertiesParser;
import windows.builders.FullSizeWindowBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        File DBConfig =  new File("/configFiles/DBConfig.xml");
        DBPropertiesParser dbPropertiesParser = new DBPropertiesParser();
        if (!DBConfig.exists())
            dbPropertiesParser.newDBProperties();
        else dbPropertiesParser.parseBDProperties();

        primaryStage = FullSizeWindowBuilder.getFullSizeScreen("mainScreen.fxml");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
