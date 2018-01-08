package windows.windowStarters;

import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;

import java.io.IOException;

public class FilmListWindow {

    public void openFilmListScreen() throws IOException {


            Stage primaryStage = new FullSizeWindowBuilder().getFullSizeScreen("FilmListWindow.fxml");
            primaryStage.show();



    }
}
