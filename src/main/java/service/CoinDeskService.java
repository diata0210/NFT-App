package service;

import java.util.List;
import java.util.Map;

import models.CoinDeskBlogModel;

/**
 * CoinDeskService
 */
public interface CoinDeskService {
    List<CoinDeskBlogModel> getAllCoin();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByMonth(String month);
    Map<String, Integer> getTagFrequencyByDay(String day);
    Map<String, Integer> getTagFrequencyByMonth(String month);
    List<CoinDeskBlogModel> getArticlesByTitle(String title);
}