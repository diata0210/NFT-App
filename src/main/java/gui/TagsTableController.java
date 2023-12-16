package gui;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.BlogModel;
import service.getAllArticles;

public class TagsTableController implements Initializable{
    @FXML
    private TableView<BlogModel> table;

    @FXML
    private TableColumn<BlogModel, String> titleCol;
    private TableColumn<BlogModel, String> descCol;
    private TableColumn<BlogModel, String> authorCol;
    private TableColumn<BlogModel, String> dateCol;
    private TableColumn<BlogModel, List<String>> relatedTagsCol;
    // private TableColumn actionCol;

    private ObservableList<BlogModel> blogList;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // String title, desc, author, date, relatedTags;
        // List<String> relatedTagsList;

        List<BlogModel> allBlogList = getAllArticles.allArticles();
        blogList = FXCollections.observableArrayList(allBlogList);

        titleCol.setCellValueFactory(new PropertyValueFactory<BlogModel, String>("title"));  
        descCol.setCellValueFactory(new PropertyValueFactory<BlogModel, String>("desc"));
        authorCol.setCellValueFactory(new PropertyValueFactory<BlogModel, String>("author"));
        dateCol.setCellValueFactory(new PropertyValueFactory<BlogModel, String>("date"));
        relatedTagsCol.setCellValueFactory(new PropertyValueFactory<BlogModel, List<String>>("relatedTags"));

        
        // for (BlogModel blog : allBlogList) {
        //     title = blog.getTitle();
        //     desc = blog.getDesc();
        //     author = blog.getAuthor();
        //     date = blog.getDate();
        //     relatedTagsList = blog.getRelatedTags();
        //     for (String tag : relatedTagsList) {
        //         relatedTags = tag + ", ";
        //     }
            

        //     table.getItems().add(blog);

        // }
        
        table.setItems(blogList); 
    }

    
}
