package windows.builders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ModalityBuilder{

    static FXMLLoader fxmlLoader = new FXMLLoader();
    static String localePath = "bundles.locale/locale";
    static Locale currentLocale = Locale.getDefault();
    static String defaultFXMLPath = "/fxml/";

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public static void setFxmlLoader(FXMLLoader fxmlLoader) {
        FullSizeWindowBuilder.fxmlLoader = fxmlLoader;
    }

    public static String getLocalePath() {
        return localePath;
    }

    public static void setLocalePath(String localePath) {
        FullSizeWindowBuilder.localePath = localePath;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        FullSizeWindowBuilder.currentLocale = currentLocale;
    }

    public static Stage getModalityScreen(String FXMLName) throws IOException {


        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();

        fxmlLoader.setLocation(ModalityBuilder.class.getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(new Scene(root));

        return primaryStage;
    }

}
