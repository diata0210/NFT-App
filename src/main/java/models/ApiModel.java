package models;

public abstract class ApiModel {
  String name;
  String description;
  String floorPrice;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getFloorPrice() {
    return floorPrice;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFloorPrice(String floorPrice) {
    this.floorPrice = floorPrice;
  }

}
