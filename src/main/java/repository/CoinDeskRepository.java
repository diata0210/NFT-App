package repository;

import java.util.List;
import java.util.Map;

import models.CoinDeskBlogModel;

public interface CoinDeskRepository {
  void loadData();

  List<CoinDeskBlogModel> getAllCoin();

  List<CoinDeskBlogModel> getArticlesByTag(String tag);

  Map<String, Integer> getTagFrequencyByMonth(String month);

  Map<String, Integer> getTagFrequencyByDay(String day);
}