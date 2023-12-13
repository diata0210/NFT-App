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

    List<TheartNewPaperBlogModel> getArticleByTags(String tag);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);
}