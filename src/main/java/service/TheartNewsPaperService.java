package service;

import java.util.List;
import java.util.Map;

import models.TheartNewPaperBlogModel;

public interface TheartNewsPaperService {
  List<TheartNewPaperBlogModel> getAllModels();

  List<TheartNewPaperBlogModel> getArticlesByTag(String tag);

  Map<String, Integer> getTagFrequencyByDay(String day);

  Map<String, Integer> getTagFrequencyByMonth(String month);
}