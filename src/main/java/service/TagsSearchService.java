package service;

import java.util.ArrayList;
import java.util.List;

import models.BlogModel;

public class TagsSearchService {

  public static List<String> searchTagsContainingString(String searchString) {
    List<String> matchingTags = new ArrayList<>();
    List<BlogModel> allArticles = GetArticles.allArticles();
    for (BlogModel article : allArticles) {
      for (String tag : article.getRelatedTags()) {
        if (tag.contains(searchString) && !matchingTags.contains(tag)) {
          matchingTags.add(tag);
        }
      }
    }
    return matchingTags;
  }
}
