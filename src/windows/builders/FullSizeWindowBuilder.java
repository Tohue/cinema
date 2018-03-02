package windows.builders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import windows.controllers.AbstractController;

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
        currentLocale = LocaleManager.getCurrentLocale();
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
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().setRoot(root);
        primaryStage.setAlwaysOnTop(true);
        ((AbstractController)fxmlLoader.getController()).setStage(primaryStage);
        ((AbstractController)fxmlLoader.getController()).setFXMLName(FXMLName);
        return primaryStage;


    }

    public Stage getFullSizeScreen(String FXMLName, Stage lastWindow) throws IOException {


        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();

        fxmlLoader.setLocation(getClass().getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Кинотеатр \"Родина\"");
        ((AbstractController)fxmlLoader.getController()).setStage(primaryStage);
        ((AbstractController)fxmlLoader.getController()).setLastWindow(lastWindow);
        ((AbstractController)fxmlLoader.getController()).setFXMLName(FXMLName);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.getScene().setRoot(root);
        return primaryStage;

    }

    public Parent getParent(String FXMLName) throws IOException {

        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();
        fxmlLoader.setLocation(getClass().getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        return root;

    }
}
