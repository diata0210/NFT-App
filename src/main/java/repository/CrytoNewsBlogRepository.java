package repository;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;

public interface CrytoNewsBlogRepository {
  void loadData();

  List<CtytoNewsBlogModel> getAllModels();

  List<CtytoNewsBlogModel> getArticleByTags(String tag);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  Map<String, Integer> getTagFrequencyByDay(String day);

}