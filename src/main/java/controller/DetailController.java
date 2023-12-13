package controller;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import service.*;
import gui.MainScreen;
import service.implement.*;
import data.util.UrlContainer;


import java.util.*;

public class DetailController {
    private VBox pane;
    @FXML
    private ScrollPane detail;
    @FXML
    private VBox box;
    @FXML
    private Label name;
    private String url;
    private CoinDeskService coinDeskService = CoinDeskServiceImp.getInstance();
    @FXML
    public void pressBackBtn(MouseEvent event){
        MainScreen.back();
    }
    @FXML
    public void goHome(MouseEvent event) {
        MainScreen.callUrl(UrlContainer.HOME_URL);
    }
    
    public DetailController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/detail.fxml"));
        loader.setController(this);
        try {
            pane = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void loadInfo(){
        Map<String,Object> value = new HashMap<>();

        for(Map.Entry<String,Object> m : value.entrySet()){
          
        }
    }
    public void add(VBox box){
        box.getChildren().add(pane);
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setName(String s){
        name.setText(s);
    }

}
