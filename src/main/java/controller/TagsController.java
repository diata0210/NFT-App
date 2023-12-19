package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.BlogModel;
import models.TagTableType;
import service.getAllTags;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class TagsController implements Initializable {
    @FXML
    public TableView<TagTableType> table;

    @FXML
    public TableColumn<TagTableType, Integer> id;

    @FXML
    public TableColumn<TagTableType, String> tag;

    public ObservableList<TagTableType> list;
@FXML
public VBox wrapTable;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
//        getAllTags tags = new getAllTags();
        int idx = 0;

        List<String> tags = getAllTags.allTags();
        list = FXCollections.observableArrayList();

        for (String tag : tags) {
            idx += 1;
            TagTableType newtag = new TagTableType(idx, tag);
            list.add(newtag);
        }
        table.setItems(list);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
    }
}
