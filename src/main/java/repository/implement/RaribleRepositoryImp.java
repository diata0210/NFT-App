package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import data.util.JsonURL;
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
                model.setFloorPrice(valueNode != null ? valueNode.asText() : "");
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
}
