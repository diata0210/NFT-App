package gui;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    // @FXML
    // private TableColumn<Blog, String> descCol;
    @FXML
    private TableColumn<Blog, String> authorCol;
    @FXML
    private TableColumn<Blog, String> dateCol;
    @FXML
    private TableColumn<Blog, String> relatedTagsCol;
    @FXML
    private TableColumn<Blog, String> actionCol;

    private ObservableList<Blog> blogList;
    private List<String> favouriteList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String title, desc, author, date, relatedTags;
        List<String> relatedTagsList;

        List<BlogModel> allBlogList = getAllArticles.allArticles();
        blogList = FXCollections.observableArrayList();

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        // descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        relatedTagsCol.setCellValueFactory(new PropertyValueFactory<>("relatedTags"));

        // Khoi tao table (in ra tat ca cac blog)
        for (BlogModel blog : allBlogList) {
            title = blog.getTitle();
            desc = blog.getDesc();
            author = blog.getAuthor();
            date = blog.getDate();
            relatedTagsList = blog.getRelatedTags();
            relatedTags = "";
            for (String tag : relatedTagsList) {
                relatedTags = tag + ", " + relatedTags;
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

    // them 1 blog vao danh sach yeu thich
    public void addToFavouriteList(String name) {
        favouriteList.add(name);
    }

    // xoa 1 blog ra khoi danh sach yeu thich
    public void removeFromFavouriteList(String name) {
        favouriteList.remove(name);
    }

    // hien thi danh sach yeu thich

    // đưa những bài viết chứa tag mà người dùng tìm kiếm ra table
    @FXML
    public void searchBlogsByTag(ActionEvent e) {
        blogList.clear();
        String title, desc, author, date, relatedTags;
        List<String> relatedTagsList;
        String tag = "NFT"; // bien luu tag can de tim kiem
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
            // gán lại những bài viết đã được thêm vào danh sách yêu thích
            blogList.add(newBlog);
        }
        table.setItems(blogList);
    }

    // Xử lý sự kiện khi nhấp vào hàng của bảng
    @FXML
    public void getShowBlogDetail(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showBlogDetail.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            showBlogDetailController controller = loader.getController();
            Blog selectedBlog = table.getSelectionModel().getSelectedItem();
            controller.setBlog(selectedBlog);
        } catch (IOException ex) {
            Logger.getLogger(TagsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}