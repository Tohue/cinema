package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;

import java.io.IOException;

public class ScheduleWindow {

    public void openSchedule() throws IOException {
        Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("ScheduleScreen.fxml");
        primaryStage.show();
    }

}
