package models;

import java.util.HashMap;
import java.util.Map;

public class ImmutableCollectionModel implements CustomModel {
    private String name;
    private String description;
    private float floorPrice;

    public ImmutableCollectionModel(String name, String description, float floorPrice) {
        this.name = name;
        this.description = description;
        this.floorPrice = floorPrice;
    }

    public ImmutableCollectionModel() {
        this.name = null;
        this.description = null;
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
        res.put("name", name);
        res.put("description", description);
        res.put("floorPrice", floorPrice);
        return res;
    }
}
