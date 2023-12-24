package repository.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import data.util.JsonURL;
import models.MintedCollectionModel;
import repository.Repository;
import repository.MintedCollectionRepository;

public class MintedCollectionRepositoryImp implements MintedCollectionRepository, Repository {
  public static MintedCollectionRepositoryImp instance;
  private List<MintedCollectionModel> models = new ArrayList<>();
  private static final double USD_TO_ETH_EXCHANGE_RATE = 0.00031; 

  private String convertUsdToEth(double usd) {
    double convertedValue = usd * USD_TO_ETH_EXCHANGE_RATE;
    return String.format("%.3f", convertedValue);
  }

  public static MintedCollectionRepositoryImp getInstance() {
    if (instance == null)
      instance = new MintedCollectionRepositoryImp();
    return instance;
  }

  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      File jsonFile = new File(JsonURL.MINTED);
      JsonNode rootNode = mapper.readTree(jsonFile);
      JsonNode edgesNode = rootNode.get("data").get("collections").get("edges");
      for (JsonNode edges : edgesNode) {
        if (edges != null) {
          MintedCollectionModel model = new MintedCollectionModel();
          JsonNode nameNode = edges.get("node").get("name");
          JsonNode descriptionNode = edges.get("node").get("description");
          JsonNode floorPriceNode = edges.get("node").get("floorPrice").get("latestFloorPrice");
          model.setName(nameNode != null ? nameNode.asText() : "");
          model.setDescription(descriptionNode != null ? descriptionNode.asText() : "");
          if (floorPriceNode != null) {
            double floorPriceUsd = floorPriceNode.asDouble();
            String floorPriceEth = convertUsdToEth(floorPriceUsd);
            model.setFloorPrice(floorPriceEth);
          } else {
            model.setFloorPrice("");
          }
          models.add(model);
        }
      }
    }

    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<MintedCollectionModel> getAllModels() {
    return models;
  }

  public List<MintedCollectionModel> findModelsByName(String name) {
    return models.stream()
        .filter(model -> model.getName().equalsIgnoreCase(name))
        .collect(Collectors.toList());
  }
}