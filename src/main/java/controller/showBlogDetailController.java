package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Blog;

public class showBlogDetailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label relatedTagsLabel;

    private Blog blog;

    public void setBlog (Blog blog){
        this.blog = blog;
        titleLabel.setText(this.blog.getTitle());
        authorLabel.setText(this.blog.getAuthor());
        dateLabel.setText(this.blog.getDate());
        relatedTagsLabel.setText(this.blog.getRelatedTags());
        descLabel.setText(this.blog.getDesc());
    }
}