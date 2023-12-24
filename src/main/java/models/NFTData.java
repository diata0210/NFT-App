package models;

public class NFTData {
  private String name;
  private String floorPrice;
  private int tagCount;

  public NFTData() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFloorPrice() {
    return floorPrice;
  }

  public void setFloorPrice(String floorPrice) {
    this.floorPrice = floorPrice;
  }

  public int getTagCount() {
    return tagCount;
  }

  public void setTagCount(int tagCount) {
    this.tagCount = tagCount;
  }

  public NFTData(String name, String floorPrice, int tagCount) {
    this.name = name;
    this.floorPrice = floorPrice;
    this.tagCount = tagCount;
  }

}
