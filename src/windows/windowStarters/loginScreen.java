package windows.windowStarters;

import config.DBPropertiesParser;
import javafx.application.Application;
import javafx.stage.Stage;
import windows.builders.ModalityBuilder;
import windows.controllers.system.DBLoginController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class loginScreen extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)   {

        try {


            Path DBConfig = Paths.get("./resources/configFiles/DBConfig.xml");
            DBPropertiesParser dbPropertiesParser = new DBPropertiesParser();
            if (!Files.exists(DBConfig)) {
                System.out.println("не парсим");
                dbPropertiesParser.newDBProperties();
            } else {
                System.out.println("будем парсить");
                dbPropertiesParser.parseBDProperties();
            }

            primaryStage = new ModalityBuilder().getModalityScreen("system/DBLogin.fxml");
            DBLoginController.setPrimaryStage(primaryStage);
            primaryStage.show();

        }catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
