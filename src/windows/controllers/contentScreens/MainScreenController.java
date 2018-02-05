package windows.controllers.contentScreens;

import database.DataLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import windows.builders.FullSizeWindowBuilder;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainScreenController extends AbstractController {

    ArrayList<Button> postersView = new ArrayList<Button>();
    ArrayList<AnchorPane> postersPanes = new ArrayList<AnchorPane>();

    @FXML
    GridPane postersPane;

    @FXML
    AnchorPane pane1;
    @FXML
    AnchorPane pane2;
    @FXML
    AnchorPane pane3;
    @FXML
    AnchorPane pane4;
    @FXML
    AnchorPane pane5;
    @FXML
    AnchorPane pane6;
    @FXML
    AnchorPane pane7;
    @FXML
    AnchorPane pane8;
    @FXML
    AnchorPane pane9;
    @FXML
    AnchorPane pane10;
    @FXML
    AnchorPane pane11;
    @FXML
    AnchorPane pane12;


    @FXML
    Button poster1;
    @FXML
    Button poster2;
    @FXML
    Button poster3;
    @FXML
    Button poster4;
    @FXML
    Button poster5;
    @FXML
    Button poster6;
    @FXML
    Button poster7;
    @FXML
    Button poster8;
    @FXML
    Button poster9;
    @FXML
    Button poster10;
    @FXML
    Button poster11;
    @FXML
    Button poster12;


    /**
     * кнопка открытия админки
     * @param actionEvent
     */
    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            ScreenStarter.Start("menu/AdminWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeThisFuckinWindow();
    }


    /**
     * Кнопка открытия расписания
     * @param actionEvent
     */
    public void openSchedule(ActionEvent actionEvent) {
        try {
            ScreenStarter.Start("ContentScreens/ScheduleScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
       closeThisFuckinWindow();
    }

    /**
     * Кнопка открытия списка фильмов
     * @param actionEvent
     */
    public void openFilmList(ActionEvent actionEvent) {

        try {
            ScreenStarter.Start("ContentScreens/FilmListWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeThisFuckinWindow();
    }

    public void initialize() {

        postersPanes.add(pane1);
        postersPanes.add(pane2);
        postersPanes.add(pane3);
        postersPanes.add(pane4);
        postersPanes.add(pane5);
        postersPanes.add(pane6);
        postersPanes.add(pane7);
        postersPanes.add(pane8);
        postersPanes.add(pane9);
        postersPanes.add(pane10);
        postersPanes.add(pane11);
        postersPanes.add(pane12);

        ArrayList<Image> posters = null;
        try {
            DataLoader.loadPosters();
            posters = DataLoader.getPostersList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Получили постеров: " + posters.size());
        if (posters.size() > 0) {
            if (posters.size() < postersView.size() - 1) {
                for (int i = 0; i < posters.size(); i ++) {
                    postersView.get(i).setGraphic(new ImageView(posters.get(i)));
                    ((ImageView)postersView.get(i).getGraphic()).fitHeightProperty().bind(postersPanes.get(i).heightProperty());
                    ((ImageView)postersView.get(i).getGraphic()).fitWidthProperty().bind(postersPanes.get(i).widthProperty());
                }
            } else {
                for (int i = 0; i < 6; i ++) {
                    System.out.println("Показываем постер номер " + i);
                    ImageView imageView = new ImageView(posters.get(i));


                    Button button = new Button();
                    button.setMaxHeight(postersPanes.get(i).getMaxHeight());
                    button.setGraphic(imageView);

                    imageView.fitHeightProperty().bind(button.heightProperty());
                    imageView.fitWidthProperty().bind(button.widthProperty());

                    button.setStyle("-fx-background-color: RED;");
                    AnchorPane.setTopAnchor(button, 0.0);
                    AnchorPane.setBottomAnchor(button, 0.0);
                    AnchorPane.setLeftAnchor(button, 0.0);
                    AnchorPane.setRightAnchor(button, 0.0);
                    postersPanes.get(i).getChildren().add(button);

                }
            }
        }

        String noRepeatCSS = "-fx-background-repeat: no-repeat;\n" +
                "-fx-background-position: center center;\n" +
                "-fx-background-attachment: fixed;\n" +
                "-fx-background-size: cover;";


//        ImageView imageView = new ImageView("/images/1.png");
//        Button button = new Button("");
//        button.setGraphic(imageView);
//        imageView.fitWidthProperty().bind(pane1.widthProperty());
//        imageView.fitHeightProperty().bind(pane1.heightProperty());
//        poster2.setGraphic(imageView);
   //     pane1.getChildren().add(button);
//        button.getGraphic().setStyle(noRepeatCSS);
//        button.setStyle(noRepeatCSS);
  //      poster2.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster3.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster4.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster5.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster6.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster7.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster8.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster9.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster10.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster11.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);
//        poster12.setStyle("-fx-background-image: url('/images/1.png');" + noRepeatCSS);



    }


    private void viewPosters() {

    }

}
