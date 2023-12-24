package models;

import java.util.List;

public abstract class BlogModel {
  String title;
  String desc;
  String author;
  String date;
  List<String> relatedTags;

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

  public List<String> getRelatedTags() {
    return relatedTags;
  }

  public void setRelatedTags(List<String> relatedTags) {
    this.relatedTags = relatedTags;
  }
}
