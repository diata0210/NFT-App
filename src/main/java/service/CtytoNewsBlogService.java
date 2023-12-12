package service;
import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;
public interface CtytoNewsBlogService {
    List<CtytoNewsBlogModel> getAllModels();
    List<String> getArticlesByTag(String tag);
    Map<String, Integer> getTagFrequencyByDay(String day);
    Map<String, Integer> getTagFrequencyByMonth(String month);
    List<String> getArticleByTitle(String title);
}
