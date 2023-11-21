package repository;

import java.util.List;

import models.BlogNFTicallyModel;

/**
 * CoinDeskRepository
 */
public interface BlogNFTicallyRepository {
    List<BlogNFTicallyModel> getAllCoin();
    List<String> getArticleByTags(String tag);
    List<String> getTagsArticleByDay(String date);
    List<String> getTagsArticleByWeek(String startDate);
    List<String> getTagsArticleByMonth(String month);
}