package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.RaribleModel;
import repository.Repository;
import repository.RaribleRepository;

public class RaribleRepositoryImp implements RaribleRepository, Repository {
    public static RaribleRepositoryImp instance;
    private List<RaribleModel> models = new ArrayList<>();

    public static RaribleRepositoryImp getInstance() {
        if (instance == null)
            instance = new RaribleRepositoryImp();
        return instance;
    }

    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File(JsonURL.RARIBLE);
            JsonNode rootNode = mapper.readTree(jsonFile);
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    RaribleModel model = new RaribleModel();
                    model.setId(node.get("id").asText());
                    model.setName(node.get("name").asText());

                    JsonNode collectionNode = node.get("collection");
                    if (collectionNode != null) {
                        model.setDescription(collectionNode.get("description").asText());
                    }

                    JsonNode statisticsNode = node.get("statistics");
                    if (statisticsNode != null) {
                        JsonNode floorPriceNode = statisticsNode.get("floorPrice");
                        if (floorPriceNode != null) {
                            JsonNode valueNode = floorPriceNode.get("value");
                            if (valueNode != null && !valueNode.isNull()) {
                                model.setFloorPrice(valueNode.floatValue());
                            }
                        }
                    }

                    models.add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RaribleModel> getAllModels() {
        return models;
    }

    public List<RaribleModel> findModelsByName(String name) {
        return models.stream()
                .filter(model -> model.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Tạo đối tượng của RaribleRepositoryImp
        RaribleRepositoryImp raribleRepository = RaribleRepositoryImp.getInstance();

        // Tải dữ liệu
        raribleRepository.loadData();

        // Lấy tất cả các mô hình và in ra
        List<RaribleModel> allModels = raribleRepository.getAllModels();
        System.out.println("All Rarible Models:");
        for (RaribleModel model : allModels) {
            System.out.println(
                    "ID: " + model.getId() + ", Name: " + model.getName() + ", Floor Price: " + model.getFloorPrice());
        }
        String searchName = "Super Creators By IAC";

    // Tìm kiếm mô hình theo tên
    System.out.println("\nModels with name '" + searchName + "':");
    List<RaribleModel> foundModels = raribleRepository.findModelsByName(searchName);
    if (foundModels.isEmpty()) {
        System.out.println("No models found with the name '" + searchName + "'.");
    } else {
        foundModels.forEach(model -> 
            System.out.println("ID: " + model.getId() + ", Name: " + model.getName() + ", Floor Price: " + model.getFloorPrice())
        );
    }
    }
}
