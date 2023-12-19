package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import data.util.UrlContainer;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainScreen extends Application {
    public void on(String[] args) {
        new Program();
        launch(args);
    }
    private static Controller controller;
    public static void back(){
        controller.back();
    }
    public static String getCurrentUrl(){
        return controller.getCurrentUrl();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Layout.fxml"));
            primaryStage.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            primaryStage.setTitle("NFTS");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}