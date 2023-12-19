package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class LayoutController{

  @FXML
  public void loadHomeScreen(ActionEvent event) {
    loadHome("/view/Home.fxml");
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

  public void loadScreen(String fxml) {
    try {
      contentArea.getChildren().clear();
      contentArea.setLayoutX(520);
      contentArea.setLayoutY(180);
      contentArea.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadHome(String fxml) {
    try {
      contentArea.getChildren().clear();
      contentArea.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}