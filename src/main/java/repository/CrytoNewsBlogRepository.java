package repository;

import java.util.List;

import models.CtytoNewsBlogModel;
/**
 * CoinDeskRepository
 */
public interface CrytoNewsBlogRepository{
    List<CtytoNewsBlogModel> getAllCoin();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByDay(String date);
    List<String> getTagsArticleByWeek(String startDate);
    List<String> getTagsArticleByMonth(String month);
}