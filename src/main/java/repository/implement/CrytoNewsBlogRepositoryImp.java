package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CtytoNewsBlogModel;
import repository.CrytoNewsBlogRepository;
import repository.Repository;

public class CrytoNewsBlogRepositoryImp implements CrytoNewsBlogRepository, Repository {
  public static CrytoNewsBlogRepositoryImp instance;
  private List<CtytoNewsBlogModel> models = new ArrayList<>();

  public static CrytoNewsBlogRepositoryImp getInstance() {
    if (instance == null)
      instance = new CrytoNewsBlogRepositoryImp();
    return instance;
  }

  @Override
  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      CtytoNewsBlogModel[] sites = mapper.readValue(new File(JsonURL.CRYTONEWS), CtytoNewsBlogModel[].class);
      for (CtytoNewsBlogModel site : sites)
        models.add(site);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<CtytoNewsBlogModel> getAllModels() {
    return models;
  }

  @Override
  public List<CtytoNewsBlogModel> getArticleByTags(String tag) {
    List<CtytoNewsBlogModel> allArticles = new ArrayList<>();
    String lowercaseTag = tag.toLowerCase();
    for (CtytoNewsBlogModel model : models) {
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
    for (CtytoNewsBlogModel model : models) {
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
    for (CtytoNewsBlogModel model : models) {
      String day = model.getDate().substring(5, 10);
      if (date != null && date.length() == 10) {
        String modelDay = date.substring(5, 10); 
        if (modelDay.equals(day)) {
          for (String tag : model.getRelatedTags()) {
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
          }
        }
      }
    }
    return tagFrequency;
  }

  public List<CtytoNewsBlogModel> getArticlesByTitle(String title) {
    List<CtytoNewsBlogModel> matchingArticles = new ArrayList<>();
    for (CtytoNewsBlogModel model : models) {
      if (model.getTitle().toLowerCase().contains(title.toLowerCase())) {
        matchingArticles.add(model);
      }
    }
    return matchingArticles;
  }
}
