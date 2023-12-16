package repository;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;
import models.PlazaNFTModel;

public interface PlazaNFTRepository {
    void loadData();

    List<PlazaNFTModel> getAllModels();

    List<PlazaNFTModel> getNFTsByTags(String tag);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);

    List<PlazaNFTModel> addFavorite(String title);

    List<PlazaNFTModel> removeFavorite(String title);
}
