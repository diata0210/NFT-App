package repository;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;
import models.CoinDeskBlogModel;

/**
 * CoinDeskRepository
 */
public interface CoinDeskRepository {
    void loadData();

    List<CoinDeskBlogModel> getAllCoin();

    List<String> getArticleByTags(String tag);

    List<String> getTagsArticleByDay(String date);

    List<String> getTagsArticleByWeek(String startDate);

    List<String> getTagsArticleByMonth(String month);;

    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);

    List<CoinDeskBlogModel> getArticlesByTitle(String title);
}