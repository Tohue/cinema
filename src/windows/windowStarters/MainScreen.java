package windows.windowStarters;


import javafx.stage.Stage;


public class MainScreen {

    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void openMainScreen() {




        try {
           ScreenStarter.Start("ContentScreens/mainScreen.fxml");
        }
         catch (Exception e) {
            e.printStackTrace();
         }
    }


}
