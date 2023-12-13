package controller;

import data.util.UrlContainer;
import gui.MainScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainScreenController {
  private BorderPane box;

    public MainScreenController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        loader.setController(this);
        try {
            box = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void add(VBox layout){
        layout.getChildren().add(box);
    }

    @FXML
    public void NhanVatLichSu(ActionEvent e){
        loadPage(UrlContainer.CHART_URL);
    }
    public void loadPage(String url){
        MainScreen.callUrl(url);
    }
}
