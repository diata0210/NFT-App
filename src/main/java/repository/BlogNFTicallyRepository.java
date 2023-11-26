package repository;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;

/**
 * CoinDeskRepository
 */
public interface BlogNFTicallyRepository{
    void loadData();
    List<BlogNFTicallyModel>getAllModels();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByDay(String date);
    List<String> getTagsArticleByWeek(String startDate);
    List<String> getTagsArticleByMonth(String month);
    Map<String, Integer> getTagFrequencyByMonth(String month);
    Map<String, Integer> getTagFrequencyByDay(String day);

}