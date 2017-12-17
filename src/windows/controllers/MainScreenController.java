package windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import windows.windowStarters.AdminWindow;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {

    @FXML
    GridPane postersPane;

    @FXML
    AnchorPane pane1;

    @FXML
    ImageView image1;

    ArrayList<ImageView> posters = new ArrayList<>();

    public void openAdminScreen(ActionEvent actionEvent) {
        try {
            new AdminWindow().openAdminScreen(((Node)actionEvent.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {


       int colCount = postersPane.getColumnCount();
       int rowColumn = postersPane.getRowCount();

       for (int i = 0; i < rowColumn; i++)
           for (int j = 0; j < colCount; j++) {

                AnchorPane imageAnchor = new AnchorPane();
                imageAnchor.setPrefWidth(100);
                imageAnchor.setPrefWidth(100);
                ImageView view = new ImageView();

                imageAnchor.getChildren().add(view);
                posters.add(view);

                view.setPreserveRatio(true);
                view.fitHeightProperty().bind(imageAnchor.heightProperty());
                view.fitWidthProperty().bind(postersPane.widthProperty());

                image1.setImage(new Image("/images/mainPhoto.jpg"));
                postersPane.add(imageAnchor, j, i);

        }
    }

}
