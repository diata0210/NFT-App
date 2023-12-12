package service;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;

public interface BlogNFTicallyService {
    List<BlogNFTicallyModel> getAllModels();

    List<String> getArticlesByTag(String tag);
    Map<String, Integer> getTagFrequencyByMonth(String month);
     Map<String, Integer> getTagFrequencyByDay(String day);
     List<String> getArticleByTitle(String title);
}
