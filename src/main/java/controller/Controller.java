package controller;
import java.util.ArrayDeque;
import java.util.Deque;

import data.util.UrlContainer;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
/**
 * Controller
 */
public class Controller{
    @FXML
    private VBox layout = new VBox();

    public void add(VBox parentLayout) {
        this.layout = parentLayout;
    }

    private Deque<String> urls = new ArrayDeque<>();
    public String getCurrentUrl(){
        return urls.getLast();
    }
    
    public void addHomePage(){
        MainScreenController controller = new MainScreenController();
        controller.add(layout);
    }
    
    // public void addListPage(String title , String url){
    //     CustomListController controller = new CustomListController();
    //     controller.setTitle(title);
    //     controller.add(layout);
    //     controller.setUrl(url);
    //     controller.setOption();
    //     controller.generateList();
    //     controller.addList();
    // }

    // public void addDetailPage(String url){
    //     DetailController controller = new DetailController();
    //     String name = url.split("/")[2];
    //     controller.setName(name);
    //     controller.setUrl(url);
    //     controller.loadInfo();
    //     controller.add(layout);
    // }
    public void resetScreen(){
        layout.getChildren().clear();
    }

    public void moveToURL(String url , boolean back){
        if(!back) urls.addLast(url);
        resetScreen();
        generateFromUrl(url);
    }
    public void back(){
        urls.removeLast();
        if(this.urls.size() > 0) moveToURL(urls.getLast(),true);
    }

    public void generateFromUrl(String url){
        // Home
        if(url.equalsIgnoreCase(UrlContainer.HOME_URL)) addHomePage();
        // List and Detail
        // if(url.equalsIgnoreCase(UrlContainer.CHART_URL)) addListPage("Biểu đồ so sánh",url );
        // else if(url.contains(UrlContainer.CHART_URL)) addDetailPage(url);
    }
}