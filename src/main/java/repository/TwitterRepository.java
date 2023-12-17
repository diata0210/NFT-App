package repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import models.TwitterModel;

public interface TwitterRepository {
    void loadData();

    List<TwitterModel> getAllModels();

    List<TwitterModel> getArticleByTags(String tag);



    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);

    List<TwitterModel> addFavorite(String title);

    List<TwitterModel> removeFavorite(String title);
}