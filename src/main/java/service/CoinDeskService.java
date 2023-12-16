package service;

import java.util.List;
import java.util.Map;

import models.CoinDeskBlogModel;
import models.TheartNewPaperBlogModel;

public interface CoinDeskService {
    List<CoinDeskBlogModel> getAllCoin();

    List<CoinDeskBlogModel> getArticlesByTag(String tag);

    Map<String, Integer> getTagFrequencyByDay(String day);

    Map<String, Integer> getTagFrequencyByMonth(String month);

    List<CoinDeskBlogModel> addFavorite(String title);

    List<CoinDeskBlogModel> removeFavorite(String title);
}