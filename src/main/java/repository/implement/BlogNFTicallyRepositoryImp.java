package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.BlogNFTicallyModel;
import repository.BlogNFTicallyRepository;
import repository.Repository;

public class BlogNFTicallyRepositoryImp implements BlogNFTicallyRepository, Repository {
  public static BlogNFTicallyRepositoryImp instance;
  private List<BlogNFTicallyModel> models = new ArrayList<>();

  public static BlogNFTicallyRepositoryImp getInstance() {
    if (instance == null)
      instance = new BlogNFTicallyRepositoryImp();
    return instance;
  }

  @Override
  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      BlogNFTicallyModel[] sites = mapper.readValue(new File(JsonURL.NFTICALLY), BlogNFTicallyModel[].class);
      for (BlogNFTicallyModel site : sites)
        models.add(site);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<BlogNFTicallyModel> getAllModels() {
    return models;
  }

  @Override
  public List<BlogNFTicallyModel> getArticleByTags(String tag) {
    List<BlogNFTicallyModel> allArticles = new ArrayList<>();
    String lowercaseTag = tag.toLowerCase();
    for (BlogNFTicallyModel model : models) {
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
    for (BlogNFTicallyModel model : models) {
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
    for (BlogNFTicallyModel model : models) {
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
}
