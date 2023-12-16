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
    private TableView<Blog> table;
    @FXML
    private TableColumn<Blog, String> titleCol;
    @FXML
    private TableColumn<Blog, String> descCol;
    @FXML
    private TableColumn<Blog, String> authorCol;
    @FXML
    private TableColumn<Blog, String> dateCol;
    @FXML
    private TableColumn<Blog, String> relatedTagsCol;
    // private TableColumn actionCol;
    private ObservableList<Blog> blogList;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        String title, desc, author, date;
        String relatedTags;
        List<String> relatedTagsList;

        List<BlogModel> allBlogList = getAllArticles.allArticles();
        blogList = FXCollections.observableArrayList(
        );

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));  
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        relatedTagsCol.setCellValueFactory(new PropertyValueFactory<>("relatedTags"));
        
        try {
            for (BlogModel blog : allBlogList) {
            if(blog.getTitle()!= null) 
                {title = blog.getTitle();
            }
            else title = "null";
            
            desc = blog.getDesc();
            author = blog.getAuthor();
            date = blog.getDate();
            relatedTagsList = blog.getRelatedTags();
            relatedTags = "";
            for (String tag : relatedTagsList) {
                relatedTags = relatedTags + ", " + tag;
            }
            Blog newBlog = new Blog();
            newBlog.setTitle(title);
            newBlog.setDesc(desc);
            newBlog.setAuthor(author);
            newBlog.setDate(date);
            newBlog.setRelatedTags(relatedTags);
            blogList.add(newBlog);
        }
        } catch (NullPointerException ex) {
            System.out.println("Exception in NPE1()" + ex);
        }
        
        table.setItems(blogList); 
    }

    // public static void main(String[] args) {
    //     List<BlogModel> allBlogList = getAllArticles.allArticles();
    //     for (BlogModel blog : allBlogList) {
    //         String tags = "";
    //         for (String tag : blog.getRelatedTags()){
    //             tags = tags + ", " + tag;
    //         }
    //         System.out.println(tags);
    //     }
    // }
}
