package service;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;
import models.TheartNewPaperBlogModel;

public interface CtytoNewsBlogService {
    List<CtytoNewsBlogModel> getAllModels();

    List<CtytoNewsBlogModel> getArticlesByTag(String tag);

    Map<String, Integer> getTagFrequencyByDay(String day);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    List<CtytoNewsBlogModel> addFavorite(String title);

    List<CtytoNewsBlogModel> removeFavorite(String title);
}
