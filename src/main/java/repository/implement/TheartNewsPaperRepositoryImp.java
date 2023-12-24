package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.TheartNewPaperBlogModel;
import repository.TheartNewsPaperRepository;
import repository.Repository;

public class TheartNewsPaperRepositoryImp implements TheartNewsPaperRepository, Repository {
  public static TheartNewsPaperRepositoryImp instance;
  private List<TheartNewPaperBlogModel> models = new ArrayList<>();

  public static TheartNewsPaperRepositoryImp getInstance() {
    if (instance == null)
      instance = new TheartNewsPaperRepositoryImp();
    return instance;
  }

  @Override
  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      TheartNewPaperBlogModel[] sites = mapper.readValue(new File(JsonURL.ARTNEWSPAPER),
          TheartNewPaperBlogModel[].class);
      for (TheartNewPaperBlogModel site : sites)
        models.add(site);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<TheartNewPaperBlogModel> getAllModels() {
    return models;
  }

  @Override
  public List<TheartNewPaperBlogModel> getArticleByTags(String tag) {
    List<TheartNewPaperBlogModel> allArticles = new ArrayList<>();
    String lowercaseTag = tag.toLowerCase();
    for (TheartNewPaperBlogModel model : models) {
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
    for (TheartNewPaperBlogModel model : models) {
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
    for (TheartNewPaperBlogModel model : models) {
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

  public List<TheartNewPaperBlogModel> getArticlesByTitle(String title) {
    List<TheartNewPaperBlogModel> matchingArticles = new ArrayList<>();
    for (TheartNewPaperBlogModel model : models) {
      if (model.getTitle().toLowerCase().contains(title.toLowerCase())) {
        matchingArticles.add(model);
      }
    }
    return matchingArticles;
  }
}
