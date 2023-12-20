package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.TagTableType;

public class TagsPopUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tagNameLabel;

    private TagTableType tag;

    public void setTag (TagTableType tag){
        this.tag = tag;
        tagNameLabel.setText(this.tag.getTag());
    }
}
