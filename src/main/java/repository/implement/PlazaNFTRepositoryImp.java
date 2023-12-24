package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.PlazaNFTModel;
import repository.PlazaNFTRepository;
import repository.Repository;

public class PlazaNFTRepositoryImp implements PlazaNFTRepository, Repository {
  public static PlazaNFTRepositoryImp instance;
  private List<PlazaNFTModel> models = new ArrayList<>();

  public static PlazaNFTRepositoryImp getInstance() {
    if (instance == null)
      instance = new PlazaNFTRepositoryImp();
    return instance;
  }

  @Override
  public void loadData() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      PlazaNFTModel[] sites = mapper.readValue(new File(JsonURL.PLAZANFT), PlazaNFTModel[].class);
      for (PlazaNFTModel site : sites)
        models.add(site);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<PlazaNFTModel> getAllModels() {
    return models;
  }

  @Override
  public List<PlazaNFTModel> getNFTsByTags(String tag) {
    List<PlazaNFTModel> allArticles = new ArrayList<>();
    String lowercaseTag = tag.toLowerCase();
    for (PlazaNFTModel model : models) {
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
    for (PlazaNFTModel model : models) {
      String month = model.getDate().substring(0, 7);
      if (date != null && date.length() == 10 && model.getDate().contains(date)) {
        String modelMonth = date.substring(0, 7); 
        if (modelMonth.equals(month)) {
          for (String tag : model.getRelatedTags()) {
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
          }
        }
      }
    }
    return tagFrequency;
  }

  public Map<String, Integer> getTagFrequencyByDay(String date) {
    Map<String, Integer> tagFrequency = new HashMap<>();
    for (PlazaNFTModel model : models) {
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