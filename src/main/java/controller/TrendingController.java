package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.NFTData;
import service.TrendingNFTService;
import models.ApiModel;

import java.util.Map;

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
        initializeColumns(); // Khởi tạo cấu trúc cột
        updateTableData();
    }


    private void initializeColumns() {
        // Kết nối cột với thuộc tính trong NFTData
        floorPrice.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tagCount.setCellValueFactory(new PropertyValueFactory<>("tagCount"));
    }


    private void updateTableData() {
        Map<ApiModel, Integer> trendingNFTsWithTagCount = trendingService.getTrendingNFTsWithTagCount();

        tableTrend.getItems().clear(); // Xóa dữ liệu cũ trước khi cập nhật mới

        for (Map.Entry<ApiModel, Integer> entry : trendingNFTsWithTagCount.entrySet()) {
            ApiModel apiModel = entry.getKey();
            Integer tagCount = entry.getValue();

            NFTData nftData = new NFTData(
                    apiModel.getName(),
                    apiModel.getFloorPrice(),
                    tagCount
            );

            tableTrend.getItems().add(nftData); // Thêm dữ liệu mới vào bảng
        }
    }

}