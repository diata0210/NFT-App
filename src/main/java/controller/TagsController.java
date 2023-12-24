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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TagTableType;
import service.GetTags;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
  public ComboBox<String> filterType;

  @FXML
  private DatePicker datePicker;


  @FXML 
  void clearTextField (MouseEvent e){
    searchByName.setText("");
  }

  @FXML
  void onSearchByName(ActionEvent event) {
    searchNameString = searchByName.getText();
    tags = GetTags.getAllTags(type, searchNameString, date);
    list.clear();
    int idx = 0;
    for (String tag : tags) {
      idx += 1;
      TagTableType newtag = new TagTableType(idx, tag);
      list.add(newtag);
    }
    table.setItems(list);
    searchByName.setText(searchNameString);
  }

  private List<String> tags = new ArrayList<>();

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
    changeDatePickerVisibility(type);
  }

  @FXML
  void onDateChange(ActionEvent event) {
    date = datePicker.getValue().toString();
    tags = GetTags.getAllTags(type, searchNameString, date);
    list.clear();
    int idx = 0;
    for (String tag : tags) {
      idx += 1;
      TagTableType newtag = new TagTableType(idx, tag);
      list.add(newtag);
    }
    table.setItems(list);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    initializeFilterType();
    initializeTable();
    initializeColumns();
  }

  private void initializeFilterType() {
    ObservableList<String> selectList = FXCollections.observableArrayList("Customize", "Filter by date",
        "Filter by week", "Filter by month");
    filterType.setItems(selectList);
  }

  private void initializeColumns() {
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
  }

  private void initializeTable() {
    list = FXCollections.observableArrayList();
    tags = GetTags.getAllTags(0, "", "");
    int idx = 0;
    for (String tag : tags) {
      idx += 1;
      TagTableType newtag = new TagTableType(idx, tag);
      list.add(newtag);
    }

    table.setItems(list);

    initializeColumns();
    datePicker.setVisible(false);
    datePicker.setValue(LocalDate.now());
  }

  @FXML
  void changeDatePickerVisibility(int type) {
    if (type == 0) {
      datePicker.setVisible(false);
    } else
      datePicker.setVisible(true);
  }
  @FXML
  public void openBlogsTable(MouseEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BlogsTable.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent, 1700, 700);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.show();
      BlogsTableController controller = loader.getController();
      TagTableType selectedTag = table.getSelectionModel().getSelectedItem();
      controller.setTable(selectedTag);
    } catch (IOException ex) {
      Logger.getLogger(BlogsTableController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
