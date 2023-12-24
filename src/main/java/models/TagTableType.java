package models;

public class TagTableType {
  public int id;
  public String tag;

  public TagTableType(int id, String tag) {
    this.id = id;
    this.tag = tag;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTags(String tag) {
    this.tag = tag;
  }
}
