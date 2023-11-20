package service;

import java.util.List;

import models.CoinDeskBlogModel;

/**
 * CoinDeskService
 */
public interface CoinDeskService {
    List<CoinDeskBlogModel> getAllCoin();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByDay(String date);
    List<String> getTagsArticleByWeek(String startDate);
    List<String> getTagsArticleByMonth(String month);
}