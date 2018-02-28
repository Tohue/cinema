package windows.controllers.contentScreens;


import database.DataLoader;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import java.io.IOException;

public class NearFilmListController extends AbstractController {

    @FXML
    FlowPane postersPane;

    public void initialize() {
        setFilms();
    }

    private void setFilms() {

        ObservableList<Film> films = new DataLoader().getPremieres();
        ImageView image;
        Button button;

        for (int i = 0; i < films.size(); i++) {


            image = new ImageView(films.get(i).getPoster());
            button = new Button(films.get(i).getName(), image);
            button.setContentDisplay(ContentDisplay.TOP);
            button.setStyle("-fx-background-color: transparent;");
            button.setFont(new Font(30));
            postersPane.setHgap(50);
            postersPane.getChildren().add(button);
            postersPane.setPadding(new Insets(50, 50, 50, 50));
            postersPane.setAlignment(Pos.TOP_CENTER);
            image.fitWidthProperty().bind(postersPane.widthProperty().divide(3));
            image.setPreserveRatio(true);
            setPosterAction(button, films.get(i));

        }

    }

    private void setPosterAction(Button button, Film film) {

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ScreenStarter.StartFilmInfoList(film, stage);
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
