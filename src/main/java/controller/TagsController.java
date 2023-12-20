package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TagTableType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TagsController implements Initializable {
    private String searchNameString;
    private int type = 0;
    private String date;
    @FXML
    public TableView<TagTableType> table;

    @FXML
    public TableColumn<TagTableType, Integer> id;

    @FXML
    public TableColumn<TagTableType, String> tag;

    @FXML
    public VBox wrapTable;
    public ObservableList<TagTableType> list;

    @FXML
    public TextField searchByName;

    @FXML
    void onSearchByName(ActionEvent event) {
        searchNameString = searchByName.getText();
        System.out.println(searchNameString);
    }

    @FXML
    public ComboBox<String> filterType;

    @FXML
    void onFilterType(ActionEvent event) {
        String typeValue = filterType.getSelectionModel().getSelectedItem().toString();
        if (typeValue.equals("Customize")) {
            type = 0;
        } else if (typeValue.equals("Filter by date")) {
            type = 1;
        } else if (typeValue.equals("Filter by week")) {
            type = 2;
        } else if (typeValue.equals("Filter by month")) {
            type = 3;
        }
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    void onDateChange(ActionEvent event) {
        date = datePicker.getValue().toString();
        System.out.println(date);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // ObservableList<String> selectList = FXCollections.observableArrayList("Customize", "Filter by date",
        //         "Filter by week", "Filter by month");
        // filterType.setItems(selectList);
        // int idx = 0;
        // List<String> tags = new ArrayList<String>();
        // list = FXCollections.observableArrayList();
        // // tags = getAllTags.allTags();
        // for (String tag : tags) {
        //     idx += 1;
        //     TagTableType newtag = new TagTableType(idx, tag);
        //     list.add(newtag);
        // }
        // table.setItems(list);
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        // tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        list = FXCollections.observableArrayList(
            new TagTableType(1, "mot"),
            new TagTableType(2, "hai"),
            new TagTableType(3, "ba")
        );
        table.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
    }

    @FXML
    public void getShowTagDetail(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TagsPopUp.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            TagsPopUpController controller = loader.getController();
            TagTableType selectedTag = table.getSelectionModel().getSelectedItem();
            controller.setTag(selectedTag);
        } catch (IOException ex) {
            Logger.getLogger(TagsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
