package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.ImmutableCollectionModel;
import repository.Repository;
import repository.ImmutableCollectionRepository;

public class ImmutableCollectionRepositoryImp implements ImmutableCollectionRepository, Repository {
    public static ImmutableCollectionRepositoryImp instance;
    private List<ImmutableCollectionModel> models = new ArrayList<>();

    public static ImmutableCollectionRepositoryImp getInstance() {
        if (instance == null)
            instance = new ImmutableCollectionRepositoryImp();
        return instance;
    }

    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File(JsonURL.IMMUTABLE);
            JsonNode rootNode = mapper.readTree(jsonFile);
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    ImmutableCollectionModel model = new ImmutableCollectionModel();
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

    public List<ImmutableCollectionModel> getAllModels() {
        return models;
    }

    public List<ImmutableCollectionModel> findModelsByName(String name) {
        return models.stream()
                .filter(model -> model.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Tạo đối tượng của ImmutableCollectionRepositoryImp
        ImmutableCollectionRepositoryImp ImmutableCollectionRepository = ImmutableCollectionRepositoryImp.getInstance();

        // Tải dữ liệu
        ImmutableCollectionRepository.loadData();

        // Lấy tất cả các mô hình và in ra
        List<ImmutableCollectionModel> allModels = ImmutableCollectionRepository.getAllModels();
        System.out.println("All ImmutableCollection Models:");
        for (ImmutableCollectionModel model : allModels) {
            System.out.println(
                    "ID: " + ", Name: " + model.getName() + ", Floor Price: " + model.getFloorPrice());
        }
        String searchName = "Super Creators By IAC";

    // Tìm kiếm mô hình theo tên
    System.out.println("\nModels with name '" + searchName + "':");
    List<ImmutableCollectionModel> foundModels = ImmutableCollectionRepository.findModelsByName(searchName);
    if (foundModels.isEmpty()) {
        System.out.println("No models found with the name '" + searchName + "'.");
    } else {
        foundModels.forEach(model -> 
            System.out.println("ID: "+ ", Name: " + model.getName() + ", Floor Price: " + model.getFloorPrice())
        );
    }
    }
}
