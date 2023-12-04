package service;

import java.util.List;

import models.BlogNFTicallyModel;

public interface BlogNFTicallyService {
    List<BlogNFTicallyModel> getAllModels();

    List<String> getArticlesByTag(String tag);

    List<String> getArticlesByDay(String date);

    List<String> getArticlesByWeek(String startDate);

    List<String> getArticlesByMonth(String month);
}
