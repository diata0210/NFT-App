package repository;

import java.util.List;

import models.TheartNewPaperBlogModel;

/**
 * CoinDeskRepository
 */
public interface TheartNewsPaperRepository{
    List<TheartNewPaperBlogModel> getAllCoin();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByDay(String date);
    List<String> getTagsArticleByWeek(String startDate);
    List<String> getTagsArticleByMonth(String month);
}