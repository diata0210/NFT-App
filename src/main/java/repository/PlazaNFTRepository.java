package repository;

import java.util.List;
import java.util.Map;

import models.PlazaNFTModel;

public interface PlazaNFTRepository {
  void loadData();

  List<PlazaNFTModel> getAllModels();

  List<PlazaNFTModel> getNFTsByTags(String tag);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  Map<String, Integer> getTagFrequencyByDay(String day);
}
