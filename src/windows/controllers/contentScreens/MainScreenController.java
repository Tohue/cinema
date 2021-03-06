package windows.controllers.contentScreens;

import database.DataLoader;
import entities.Film;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import windows.builders.LocaleManager;
import windows.components.FontLoader;
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
    Button premieresBtn;
    @FXML
    Button filmListBtn;
    @FXML
    Button scheduleBtn;

    @FXML
    Separator bottomSeparator;

    @FXML
    Label mainTitle;
    @FXML
    AnchorPane bottomPane;

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
    @FXML
    Button adminBtn;

    // Преключатель языков
    @FXML
    ToggleButton en_toggle;
    @FXML
    ToggleButton ru_toggle;

    @FXML
    ImageView logoView;

    /**
     * кнопка открытия админки
     * @param actionEvent
     */
    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            ScreenStarter.Start("system/AdminPanelLogIn.fxml", stage);
            close();
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
            ScreenStarter.Start("contentScreens/ScheduleScreen.fxml", stage);
            close();
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
            ScreenStarter.Start("contentScreens/FilmListWindow.fxml", stage);
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeThisFuckinWindow();
    }

    public void initialize() {

        setLanguageToggle();
        setLanguageIcons();
        setLogo();
        setReflection();
        loadPosters();
        setFonts();

    }

    private void loadPosters() {

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
     //   image.setPreserveRatio(true);
        image.fitHeightProperty().bind(postersPane.heightProperty().divide(2));
        image.fitWidthProperty().bind(postersPane.widthProperty().divide(6));
        button.setGraphic(image);
        filmKeys.put(button, film);
        image.setStyle("-fx-background-radius: 20px;");

        button.setOnAction(event -> {
            try {
                ScreenStarter.StartFilmInfoList(film, stage);
                close();
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

    public void openPremieres() {

        try {
            ScreenStarter.Start("contentScreens/NearFilmList.fxml", stage);
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setLanguageIcons() {

        try {
            ImageView en_flag = new ImageView(new Image(getClass().getResourceAsStream("/images/flag_en.png")));
            ImageView ru_flag = new ImageView(new Image(getClass().getResourceAsStream("/images/flag_ru.png")));

            en_flag.fitHeightProperty().bind(adminBtn.heightProperty().multiply(1.5));
            en_toggle.setStyle("-fx-background-color: transparent");
            en_flag.setPreserveRatio(true);

            ru_flag.fitHeightProperty().bind(adminBtn.heightProperty().multiply(1.5));
            ru_toggle.setStyle("-fx-background-color: transparent");
            ru_flag.setPreserveRatio(true);


            en_toggle.setGraphic(en_flag);
            ru_toggle.setGraphic(ru_flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLogo() {

        try {
            Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
            logoView.setImage(logo);
            logoView.setFitWidth(200);
            logoView.setPreserveRatio(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setReflection() {

//        premieresBtn.getStyl().add("-fx-background-radius: 70;");
//        filmListBtn.getStylesheets().add("-fx-background-radius: 70;");
//        scheduleBtn.getStylesheets().add("-fx-background-radius: 70;");

        premieresBtn.setStyle("-fx-background-radius: 70;" +
                "-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-\t: 10;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "\n" +
                "    -fx-padding: 10 20 10 20;");

        filmListBtn.setStyle("-fx-background-radius: 70;" +
                "-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-\t: 10;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "\n" +
                "    -fx-padding: 10 20 10 20;");

        scheduleBtn.setStyle("-fx-background-radius: 70;" +
                "-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-\t: 10;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "\n" +
                "    -fx-padding: 10 20 10 20;");

        premieresBtn.setEffect(new Reflection());
        filmListBtn.setEffect(new Reflection());
        scheduleBtn.setEffect(new Reflection());

    }

    private void setFonts() {

        FontLoader fontLoader = new FontLoader();
        mainTitle.setFont(fontLoader.getLemon(85));

        en_toggle.setFont(fontLoader.getBebasThin(20));
        ru_toggle.setFont(fontLoader.getBebasThin(20));

        premieresBtn.setFont(fontLoader.getBebasReg(35));
        scheduleBtn.setFont(fontLoader.getBebasReg(35));
        filmListBtn.setFont(fontLoader.getBebasReg(35));

        adminBtn.setFont(fontLoader.getBebasThin(15));

    }

}
