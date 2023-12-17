package repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import models.TheartNewPaperBlogModel;

public interface TheartNewsPaperRepository {
    void loadData();

    List<TheartNewPaperBlogModel> getAllModels();

    List<TheartNewPaperBlogModel> getArticleByTags(String tag);


    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);

    List<TheartNewPaperBlogModel> addFavorite(String title);

    List<TheartNewPaperBlogModel> removeFavorite(String title);
}