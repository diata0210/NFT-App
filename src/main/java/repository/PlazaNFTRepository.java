package repository;

import java.util.List;
import java.util.Map;

import models.PlazaNFTModel;

public interface PlazaNFTRepository {
    void loadData();

    List<PlazaNFTModel> getAllModels();

    List<String> getNFTsByTags(String tag);

    List<String> getTagsByDate(String date);

    List<String> getTagsByWeek(String startDate);

    List<String> getTagsByMonth(String month);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    Map<String, Integer> getTagFrequencyByDay(String day);
    List<PlazaNFTModel> getArticlesByTitle(String title);
}
