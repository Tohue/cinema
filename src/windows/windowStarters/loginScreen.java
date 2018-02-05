package windows.windowStarters;

import config.DBPropertiesParser;
import javafx.application.Application;
import javafx.stage.Stage;
import windows.builders.ModalityBuilder;
import windows.controllers.system.DBLoginController;

import java.io.File;

public class loginScreen extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

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

        primaryStage = new ModalityBuilder().getModalityScreen("system/DBLogin.fxml");
        DBLoginController.setPrimaryStage(primaryStage);
        primaryStage.show();

    }
}
