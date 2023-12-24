package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Map;

import models.NFTData;
import service.TrendingNFTService;
import models.ApiModel;

public class TrendingController {

  @FXML
  private TableColumn<NFTData, Double> floorPrice;

  @FXML
  private TableColumn<NFTData, String> name;

  @FXML
  private TableView<NFTData> tableTrend;

  @FXML
  private TableColumn<NFTData, Integer> tagCount;

  private TrendingNFTService trendingService = new TrendingNFTService();

  @FXML
  public void initialize() {
    initializeColumns(); 
    updateTableData();
  }

  private void initializeColumns() {
    floorPrice.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    tagCount.setCellValueFactory(new PropertyValueFactory<>("tagCount"));
  }

  private void updateTableData() {
    Map<ApiModel, Integer> trendingNFTsWithTagCount = trendingService.getTrendingNFTsWithTagCount();

    tableTrend.getItems().clear(); 

    for (Map.Entry<ApiModel, Integer> entry : trendingNFTsWithTagCount.entrySet()) {
      ApiModel apiModel = entry.getKey();
      Integer tagCount = entry.getValue();

      NFTData nftData = new NFTData(
          apiModel.getName(),
          apiModel.getFloorPrice(),
          tagCount);

      tableTrend.getItems().add(nftData); 
    }
  }

}