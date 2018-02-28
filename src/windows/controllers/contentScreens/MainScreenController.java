package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import windows.builders.LocaleManager;
import windows.controllers.AbstractController;
import windows.windowStarters.ScreenStarter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class MainScreenController extends AbstractController {

    private final ArrayList<Button> postersView = new ArrayList<>();
    private HashMap<Button, Film> filmKeys = new HashMap<>();

    @FXML
    GridPane postersPane;

    @FXML
    Separator bottomSeparator;

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

    // Преключатель языков
    @FXML
    ToggleButton en_toggle;
    @FXML
    ToggleButton ru_toggle;

    /**
     * кнопка открытия админки
     * @param actionEvent
     */
    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            ScreenStarter.Start("system/AdminPanelLogIn.fxml", stage);
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
            ScreenStarter.Start("ContentScreens/ScheduleScreen.fxml", stage);
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
            ScreenStarter.Start("ContentScreens/FilmListWindow.fxml", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeThisFuckinWindow();
    }

    public void initialize() {


        postersView.add(poster1);
        postersView.add(poster2);
        postersView.add(poster3);
        postersView.add(poster4);
        postersView.add(poster5);
        postersView.add(poster6);
        postersView.add(poster7);
        postersView.add(poster8);
        postersView.add(poster9);
        postersView.add(poster10);
        postersView.add(poster11);
        postersView.add(poster12);

        ObservableList<Film> films;
        try {

            DataLoader dataLoader = new DataLoader();
            dataLoader.loadFilmInfo();
            films = dataLoader.getFilmInfoList();

            System.out.println("Получили фильмов: " + films.size());
            if (films.size() > 0) {
                if (films.size() < postersView.size() - 1) {
                    for (int i = 0; i < films.size(); i ++)
                        setPoster(postersView.get(i), films.get(i));
                } else {
                    for (int i = 0; i < postersView.size(); i ++)
                        setPoster(postersView.get(i), films.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void setPoster(Button button, Film film) {

        ImageView image = new ImageView(film.getPoster());
        image.setPreserveRatio(true);
        image.fitHeightProperty().bind(postersPane.heightProperty().divide(2));
        button.setGraphic(image);
        filmKeys.put(button, film);


        button.setOnAction(event -> {
            try {
                ScreenStarter.StartFilmInfoList(film, stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void setLanguageToggle() {

        if (LocaleManager.getCurrentLocale().equals(new Locale("ru")))
            ru_toggle.setSelected(true);
        else en_toggle.setSelected(true);

    }

    public void setENLocale() {
        LocaleManager.setCurrentLocale(new Locale("en"));
      reload();
    }

    public void setRULocale() {
        LocaleManager.setCurrentLocale(new Locale("ru"));
        reload();
    }

}
