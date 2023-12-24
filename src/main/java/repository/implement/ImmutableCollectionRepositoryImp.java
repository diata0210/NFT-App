package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import data.util.JsonURL;
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

      JsonNode resultsNode = rootNode.get("result");
      for (JsonNode result : resultsNode) {
        if (result != null) {
          ImmutableCollectionModel model = new ImmutableCollectionModel();
          JsonNode nameNode = result.get("name");
          JsonNode descriptionNode = result.get("description");
          JsonNode floorPriceNode = result.get("stats").get("volume_24h").get("tokens").get("ETH")
              .get("value");

          model.setName(nameNode != null ? nameNode.asText() : "");
          model.setDescription(descriptionNode != null ? descriptionNode.asText() : "");
          if (floorPriceNode != null) {
            double floorPrice = floorPriceNode.asDouble();
            String formattedFloorPrice = String.format("%.2f", floorPrice);
            model.setFloorPrice(formattedFloorPrice);
          } else {
            model.setFloorPrice("");
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
}