package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import data.util.JsonURL;
import models.EbisuBayModel;
import repository.Repository;
import repository.EbisuBayRepository;

public class EbisuBayRepositoryImp implements EbisuBayRepository, Repository {
  public static EbisuBayRepositoryImp instance;
  private List<EbisuBayModel> models = new ArrayList<>();

  public static EbisuBayRepositoryImp getInstance() {
    if (instance == null)
      instance = new EbisuBayRepositoryImp();
    return instance;
  }

  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      File jsonFile = new File(JsonURL.EBISUBAY);
      JsonNode rootNode = mapper.readTree(jsonFile);

      JsonNode collectionNode = rootNode.get("collections");
      if (collectionNode != null && collectionNode.isArray()) {
        for (JsonNode collection : collectionNode) {
          if (collection != null) {
            EbisuBayModel model = new EbisuBayModel();
            JsonNode nameNode = collection.get("name");
            JsonNode descriptionNode = collection.get("metadata").get("description");
            JsonNode floorPriceNode = collection.get("stats").get("total").get("floorPrice");

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

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<EbisuBayModel> getAllModels() {
    return models;
  }

  public List<EbisuBayModel> findModelsByName(String name) {
    return models.stream()
        .filter(model -> model.getName().equalsIgnoreCase(name))
        .collect(Collectors.toList());
  }

}