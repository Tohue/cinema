package windows.controllers;

import javafx.stage.Stage;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class AbstractController {

    private Stage lastWindow;

    private String FXMLName;

    private ResourceBundle bundle;

    protected Stage stage;

    public void setFXMLName(String FXMLName) {
        this.FXMLName = FXMLName;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLastWindow(Stage stage) { this.lastWindow = stage; }

    public void closeThisFuckinWindow() {
        stage.close();
    }

    public void close() {
        stage.close();
    }

    public Stage getLastWindow() {
        return lastWindow;
    }

    public void openLastWindow() {
        lastWindow.setFullScreen(true);
        lastWindow.show(); }

    public void back() {
        openLastWindow();
        closeThisFuckinWindow();
    }

    public void reload() {

        close();
        try {
            ScreenStarter.Start(FXMLName, lastWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
