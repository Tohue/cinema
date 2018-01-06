package windows.builders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FullSizeWindowBuilder{

    private  FXMLLoader fxmlLoader;
    private  String localePath;
    private  Locale currentLocale;
    private  String defaultFXMLPath;

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
       this.fxmlLoader  = fxmlLoader;
    }

    public  String getLocalePath() {
        return localePath;
    }

    public  void setLocalePath(String localePath) {
        this.localePath = localePath;
    }

    public  Locale getCurrentLocale() {
        return currentLocale;
    }

    public  void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Stage getFullSizeScreen(String FXMLName) throws IOException {


        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();

        fxmlLoader.setLocation(getClass().getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        primaryStage.setMaximized(true);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/fonts/fonts.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getScene().setRoot(root);
        return primaryStage;
    }
}
