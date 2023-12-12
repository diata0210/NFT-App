package repository;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;
import models.TheartNewPaperBlogModel;

/**
 * CoinDeskRepository
 */
public interface TheartNewsPaperRepository {
    void loadData();
    List<TheartNewPaperBlogModel> getAllModels();

    List<String> getArticleByTags(String tag);

    List<String> getTagsArticleByDay(String date);

    List<String> getTagsArticleByWeek(String startDate);

    List<String> getTagsArticleByMonth(String month);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);
    List<String> getArticleByTitle(String title);
}