package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class LayoutController {

  @FXML
  public void loadHomeScreen(ActionEvent event) {
    loadScreen("/view/Home.fxml");
  }

  @FXML
  public void loadNftTagsScreen(ActionEvent event) {
    loadScreen("/view/NFTTags.fxml");
  }

  @FXML
  void loadTreding(ActionEvent event) {
    loadScreen("/view/TrendingNFT.fxml");
  }

  @FXML
  private StackPane contentArea;

  private void loadScreen(String fxml) {
    try {
      if (contentArea != null) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    loadHomeScreen();
  }

  private void loadHomeScreen() {
    loadScreen("/view/Home.fxml");
  }
}