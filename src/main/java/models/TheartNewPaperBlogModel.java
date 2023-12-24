package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheartNewPaperBlogModel extends BlogModel implements CustomModel {
  public TheartNewPaperBlogModel(String title, String desc, String author, String date, List<String> relatedTags) {
    super.title = title;
    super.desc = desc;
    super.author = author;
    super.date = date;
    super.relatedTags = relatedTags;
  }

  public TheartNewPaperBlogModel() {
    super.title = null;
    super.desc = null;
    super.author = null;
    super.date = null;
    super.relatedTags = null;
  }

  @Override
  public Map<String, Object> MapDescription() {
    Map<String, Object> res = new HashMap<>();
    res.put("title", title);
    res.put("author", author);
    res.put("desc", desc);
    res.put("relatedTags", relatedTags);
    res.put("date", date);
    return res;
  }
}
