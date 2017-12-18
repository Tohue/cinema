package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;

import java.io.IOException;

public class FilmListWindow {

    public void openFilmListScreen() {

        try {
            Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("FilmListWindow.fxml");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
