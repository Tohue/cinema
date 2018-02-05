package windows.builders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import windows.controllers.AbstractController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ModalityBuilder{

     private  FXMLLoader fxmlLoader;
     private  String localePath;
     private  Locale currentLocale;
     private  String defaultFXMLPath;

    public ModalityBuilder() {

        fxmlLoader = new FXMLLoader();
        localePath = "bundles.locale/locale";
        currentLocale = Locale.getDefault();
        defaultFXMLPath = "/fxml/";

    }

    public ModalityBuilder(FXMLLoader fxmlLoader, String localePath, Locale currentLocale, String defaultFXMLPath) {

        this.fxmlLoader = fxmlLoader;
        this.localePath = localePath;
        this.currentLocale = currentLocale;
        this.defaultFXMLPath = defaultFXMLPath;
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public String getLocalePath() {
        return localePath;
    }

    public void setLocalePath(String localePath) {
        this.localePath = localePath;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Stage getModalityScreen(String FXMLName) throws IOException {


        String currFXML = defaultFXMLPath + FXMLName;
        Stage primaryStage = new Stage();

        fxmlLoader.setLocation(ModalityBuilder.class.getResource(currFXML));
        fxmlLoader.setResources(ResourceBundle.getBundle(localePath, currentLocale));
        Parent root = fxmlLoader.load();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().setRoot(root);
        ((AbstractController)fxmlLoader.getController()).setStage(primaryStage);
        return primaryStage;
    }

}
