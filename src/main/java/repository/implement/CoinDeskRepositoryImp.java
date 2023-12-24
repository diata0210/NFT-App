package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CoinDeskBlogModel;
import repository.CoinDeskRepository;
import repository.Repository;

public class CoinDeskRepositoryImp implements CoinDeskRepository, Repository {
  public static CoinDeskRepositoryImp instance;
  private List<CoinDeskBlogModel> models = new ArrayList<>();

  public static CoinDeskRepositoryImp getInstance() {
    if (instance == null)
      instance = new CoinDeskRepositoryImp();
    return instance;
  }

  @Override
  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      CoinDeskBlogModel[] sites = mapper.readValue(new File(JsonURL.COINDESK), CoinDeskBlogModel[].class);
      for (CoinDeskBlogModel site : sites)
        models.add(site);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<CoinDeskBlogModel> getAllCoin() {
    return models;
  }

  @Override
  public List<CoinDeskBlogModel> getArticlesByTag(String tag) {
    List<CoinDeskBlogModel> allArticles = new ArrayList<>();
    String lowercaseTag = tag.toLowerCase();
    for (CoinDeskBlogModel model : models) {
      List<String> lowercaseTags = model.getRelatedTags().stream()
          .map(String::toLowerCase)
          .collect(Collectors.toList());
      if (lowercaseTags.contains(lowercaseTag)) {
        allArticles.add(model);
        System.out.println(model.getTitle());
      }
    }
    return allArticles;
  }

  public Map<String, Integer> getTagFrequencyByMonth(String date) {
    Map<String, Integer> tagFrequency = new HashMap<>();
    for (CoinDeskBlogModel model : models) {
      if (model.getDate() != null && model.getDate().length() >= 7) {
        String month = model.getDate().substring(0, 7);
        if (date != null && date.length() >= 7 && model.getDate().contains(date)) {
          String modelMonth = date.substring(0, 7); 
          if (modelMonth.equals(month)) {
            for (String tag : model.getRelatedTags()) {
              tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            }
          }
        }
      }
    }
    return tagFrequency;
  }

  public Map<String, Integer> getTagFrequencyByDay(String date) {
    Map<String, Integer> tagFrequency = new HashMap<>();
    for (CoinDeskBlogModel model : models) {
      String day = model.getDate();
      if (date != null && date.length() == 10) {
        String modelDay = date; 
        if (modelDay.equals(day)) {
          for (String tag : model.getRelatedTags()) {
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
          }
        }
      }
    }
    return tagFrequency;
  }

  public List<CoinDeskBlogModel> getArticlesByTitle(String title) {
    List<CoinDeskBlogModel> matchingArticles = new ArrayList<>();
    for (CoinDeskBlogModel model : models) {
      if (model.getTitle().toLowerCase().contains(title.toLowerCase())) {
        matchingArticles.add(model);
      }
    }
    return matchingArticles;
  }
}
