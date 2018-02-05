package windows.controllers;

import javafx.stage.Stage;

public abstract class AbstractController {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeThisFuckinWindow() {
        stage.close();
    }
}
