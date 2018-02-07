package windows.controllers;

import javafx.stage.Stage;

public abstract class AbstractController {

    private Stage lastWindow;

    protected Stage stage;

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

    public void openLastWindow() {
        lastWindow.setFullScreen(true);
        lastWindow.show(); }

    public void back() {
        openLastWindow();
        closeThisFuckinWindow();
    }
}
