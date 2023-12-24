package service;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;

public interface CrytoNewsBlogService {
  List<CtytoNewsBlogModel> getAllModels();

  List<CtytoNewsBlogModel> getArticlesByTag(String tag);

  Map<String, Integer> getTagFrequencyByDay(String day);

  Map<String, Integer> getTagFrequencyByMonth(String month);
}
