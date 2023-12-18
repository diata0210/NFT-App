package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LayoutController extends Controller {
    private Controller controller = new Controller();


    @FXML
    public void loadHomeScreen(ActionEvent event) {
        loadScreen("/view/Home.fxml");
    }

    @FXML
    public void loadNftTagsScreen(ActionEvent event) {
        loadScreen("/view/NFTTags.fxml");
    }

    @FXML
    void loadMarketPlaceScreen(ActionEvent event) {
        loadScreen("/view/MarketPlace.fxml");
    }
    @FXML
    void loadSearch(ActionEvent event) {
        loadScreen("/view/Search.fxml");
    }
    @FXML
    private StackPane contentArea;

    public void loadScreen(String fxml){
        try {
            contentArea.getChildren().clear();
            contentArea.setLayoutX(280);
            contentArea.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}