package service;

import java.util.List;
import java.util.Map;

import models.TwitterModel;

public interface TwitterService {
  List<TwitterModel> getAllModels();

  List<TwitterModel> getArticlesByTag(String tag);

  Map<String, Integer> getTagFrequencyByDay(String day);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  List<TwitterModel> addFavorite(String title);

  List<TwitterModel> removeFavorite(String title);
}