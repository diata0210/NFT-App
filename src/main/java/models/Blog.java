package models;

public class Blog {
    String title;
    String desc;
    String author;
    String date;
    String relatedTags;

    public Blog(){
        this.title = null;
        this.desc = null;
        this.author = null;
        this.date = null;
        this.relatedTags = null;
    }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getRelatedTags() {
    return relatedTags;
  }

  public void setRelatedTags(String relatedTags) {
    this.relatedTags = relatedTags;
  }
}