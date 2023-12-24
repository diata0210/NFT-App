package service;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;

public interface BlogNFTicallyService {
  List<BlogNFTicallyModel> getAllModels();

  List<BlogNFTicallyModel> getArticlesByTag(String tag);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  Map<String, Integer> getTagFrequencyByDay(String day);
}
