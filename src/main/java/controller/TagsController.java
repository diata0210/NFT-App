package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.TagTableType;

import java.net.URL;
import java.util.ResourceBundle;

public class TagsController implements Initializable {
    @FXML
    public TableView<TagTableType> table;

    @FXML
    public TableColumn<TagTableType, Integer> id;

    @FXML
    public TableColumn<TagTableType, String> tag;
    public ObservableList<TagTableType> list;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        list = FXCollections.observableArrayList();

        list.add(new TagTableType(20, "hahah"));
        list.add(new TagTableType(25, "alice@example.com"));
        list.add(new TagTableType(30, "bob@example.com"));

        table.setItems(list);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
    }


}
