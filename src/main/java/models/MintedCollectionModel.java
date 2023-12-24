package models;

import java.util.HashMap;
import java.util.Map;

public class MintedCollectionModel extends ApiModel implements CustomModel {

  public MintedCollectionModel(String name, String description, String floorPrice) {
    super.name = name;
    super.description = description;
    super.floorPrice = floorPrice;
  }

  public MintedCollectionModel() {
    super.floorPrice = null;
    super.name = null;
    super.description = null;
  }

  @Override
  public Map<String, Object> MapDescription() {
    Map<String, Object> res = new HashMap<>();
    res.put("name", name);
    res.put("description", description);
    res.put("floorPrice", floorPrice);
    return res;
  }
}