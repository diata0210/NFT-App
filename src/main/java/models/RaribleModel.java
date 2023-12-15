package models;

import java.util.HashMap;
import java.util.Map;

public class RaribleModel implements CustomModel {
    private String id;
    private String name;
    private String description;
    private float floorPrice;
  
    public RaribleModel(String id, String name, String description, float floorPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.floorPrice = floorPrice;
    }

    public RaribleModel() {
        this.id=null;
        this.name=null;
        this.description = null;
    }

    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getFloorPrice() {
        return floorPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFloorPrice(float floorPrice) {
        this.floorPrice = floorPrice;
    }

    @Override
    public Map<String, Object> MapDescription() {
        Map<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("name", name);
        res.put("description", description);
        res.put("floorPrice", floorPrice);
        return res;
    }
}
