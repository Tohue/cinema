package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;

import java.io.IOException;

public class BackWindow {
    public void openBack() throws IOException {
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("BackLoadingScreen.fxml");
        primaryStage.show();
    }
}
