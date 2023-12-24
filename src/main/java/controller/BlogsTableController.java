package controller;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
<<<<<<< HEAD
=======

import models.Blog;
>>>>>>> 40ab100 (format code)
import models.BlogModel;
import models.TagTableType;
import models.BlogTableType;
import service.ArticleSearchService;

public class BlogsTableController implements Initializable {
<<<<<<< HEAD
    @FXML
    private TableView<BlogTableType> table;
    @FXML
    private TableColumn<BlogTableType, String> titleCol;
    @FXML
    private TableColumn<BlogTableType, String> desCol;
    @FXML
    private TableColumn<BlogTableType, String> authorCol;
    @FXML
    private TableColumn<BlogTableType, String> dateCol;
    @FXML
    private TableColumn<BlogTableType, String> relatedTagsCol;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
=======
  @FXML
  private TableView<Blog> table;
  @FXML
  private TableColumn<Blog, String> titleCol;
  @FXML
  private TableColumn<Blog, String> desCol;
  @FXML
  private TableColumn<Blog, String> authorCol;
  @FXML
  private TableColumn<Blog, String> dateCol;
  @FXML
  private TableColumn<Blog, String> relatedTagsCol;
>>>>>>> 40ab100 (format code)

  @FXML
  private ResourceBundle resources;
  @FXML
  private URL location;

  @FXML
  private Label tagNameLabel;

<<<<<<< HEAD
    private ObservableList<BlogTableType> blogList;

    public void setTable(TagTableType tag) {
        this.tag = tag;
        tagNameLabel.setText("#" + this.tag.getTag());

        String title, desc, author, date, relatedTags;
        List<String> relatedTagsList;
        List<BlogModel> returnedList = ArticleSearchService.searchArticlesByTag(this.tag.getTag());
        blogList = FXCollections.observableArrayList();
=======
  private TagTableType tag;

  private ObservableList<Blog> blogList;
>>>>>>> 40ab100 (format code)

  public void setTable(TagTableType tag) {
    this.tag = tag;
    tagNameLabel.setText("#" + this.tag.getTag());

<<<<<<< HEAD
        for (BlogModel blog : returnedList) {
            title = blog.getTitle();
            desc = blog.getDesc();
            author = blog.getAuthor();
            date = blog.getDate();
            relatedTagsList = blog.getRelatedTags();
            relatedTags = "";
            for (String relatedTag : relatedTagsList) {
                relatedTags = relatedTag + ", " + relatedTags;
            }
            BlogTableType newBlog = new BlogTableType();
            newBlog.setTitle(title);
            newBlog.setDesc(desc);
            newBlog.setAuthor(author);
            newBlog.setDate(date);
            newBlog.setRelatedTags(relatedTags);
            blogList.add(newBlog);
        }
        table.setItems(blogList);
=======
    String title, desc, author, date, relatedTags;
    List<String> relatedTagsList;
    List<BlogModel> returnedList = ArticleSearchService.searchArticlesByTag(this.tag.getTag());
    blogList = FXCollections.observableArrayList();

    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    desCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
    authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    relatedTagsCol.setCellValueFactory(new PropertyValueFactory<>("relatedTags"));

    for (BlogModel blog : returnedList) {
      title = blog.getTitle();
      desc = blog.getDesc();
      author = blog.getAuthor();
      date = blog.getDate();
      relatedTagsList = blog.getRelatedTags();
      relatedTags = "";
      for (String relatedTag : relatedTagsList) {
        relatedTags = relatedTag + ", " + relatedTags;
      }
      Blog newBlog = new Blog();
      newBlog.setTitle(title);
      newBlog.setDesc(desc);
      newBlog.setAuthor(author);
      newBlog.setDate(date);
      newBlog.setRelatedTags(relatedTags);
      blogList.add(newBlog);
>>>>>>> 40ab100 (format code)
    }
    table.setItems(blogList);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

}