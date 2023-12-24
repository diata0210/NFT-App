package repository;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;

public interface BlogNFTicallyRepository {
  void loadData();

  List<BlogNFTicallyModel> getAllModels();

  List<BlogNFTicallyModel> getArticleByTags(String tag);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  Map<String, Integer> getTagFrequencyByDay(String day);
}