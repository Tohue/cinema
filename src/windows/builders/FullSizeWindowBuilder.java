package windows.builders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FullSizeWindowBuilder{

    FXMLLoader fxmlLoader;
    String localePath;
    Locale currentLocale;
    String defaultFXMLPath;

    public FullSizeWindowBuilder(FXMLLoader fxmlLoader, String localePath, Locale currentLocale, String defaultFXMLPath) {
        this.fxmlLoader = fxmlLoader;
        this.localePath = localePath;
        this.currentLocale = currentLocale;
        this.defaultFXMLPath = defaultFXMLPath;
    }

    public FullSizeWindowBuilder() {

        fxmlLoader = new FXMLLoader();
        localePath = "bundles.locale/locale";
        currentLocale = Locale.getDefault();
        defaultFXMLPath = "/fxml/";
    }

    public  FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public  void setFxmlLoader(FXMLLoader fxmlLoader) {
        fxmlLoader = fxmlLoader;
    }

    public  String getLocalePath() {
        return localePath;
    }

    public  void setLocalePath(String localePath) {
        localePath = localePath;
    }

    public  Locale getCurrentLocale() {
        return currentLocale;
    }

    public  void setCurrentLocale(Locale currentLocale) {
        currentLocale = currentLocale;
    }

    public Stage getFullSizeScreen(String FXMLName) throws IOException {


        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();

        fxmlLoader.setLocation(getClass().getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().setRoot(root);
        return primaryStage;
    }
}
