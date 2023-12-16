package gui;

import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.BlogModel;
import service.getAllArticles;
import service.ArticleSearchService;

public class TagsTableController implements Initializable {
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
    @FXML
    private TableColumn<Blog, String> actionCol;

    private ObservableList<Blog> blogList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String title, desc, author, date, relatedTags;
        List<String> relatedTagsList;

        List<BlogModel> allBlogList = getAllArticles.allArticles();
        blogList = FXCollections.observableArrayList();

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        relatedTagsCol.setCellValueFactory(new PropertyValueFactory<>("relatedTags"));
        //tao button cho moi table cell
        actionCol.setCellValueFactory(new PropertyValueFactory<>("dummy"));

        Callback<TableColumn<Blog, String>, TableCell<Blog, String>> cellFactory = 
        new Callback<TableColumn<Blog, String>, TableCell<Blog, String>>() {
            @Override
            public TableCell<Blog, String> call(final TableColumn<Blog, String> param) {
                final TableCell<Blog, String> cell = new TableCell<Blog, String>() {

                    final Button btn = new Button("Add");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                String text = btn.getText();
                                if (text == "Add") {
                                    btn.setText("Added");
                                }
                                else {
                                    btn.setText("Add");
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        actionCol.setCellFactory(cellFactory);

        // Khoi tao table (in ra tat ca cac blog)
        for (BlogModel blog : allBlogList) {
            title = blog.getTitle();
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
        table.setItems(blogList);
    }

    // đưa những bài viết chứa tag mà người dùng tìm kiếm ra table
    @FXML
    public void searchBlogsByTag() {
        table.getItems().clear();
        String title, desc, author, date, relatedTags;
        List<String> relatedTagsList;
        String tag = "#NFT"; //bien luu tag can de tim kiem
        List<BlogModel> returnedList = ArticleSearchService.searchArticlesByTag(tag);
        for (BlogModel blog : returnedList) {
            title = blog.getTitle();
            desc = blog.getDesc();
            author = blog.getAuthor();
            date = blog.getDate();
            relatedTagsList = blog.getRelatedTags();
            relatedTags = "";
            for (String relatedTag : relatedTagsList) {
                relatedTags = relatedTags + ", " + relatedTag;
            }
            Blog newBlog = new Blog();
            newBlog.setTitle(title);
            newBlog.setDesc(desc);
            newBlog.setAuthor(author);
            newBlog.setDate(date);
            newBlog.setRelatedTags(relatedTags);
            blogList.add(newBlog);
        }
        table.setItems(blogList);
    }

    @FXML
    public void getShowBlogDetail (MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("showBlogDetail.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            Logger.getLogger(TagsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
