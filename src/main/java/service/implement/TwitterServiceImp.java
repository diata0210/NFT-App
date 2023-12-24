package service.implement;

import java.util.List;
import java.util.Map;

import models.TwitterModel;
import repository.TwitterRepository;
import repository.implement.TwitterRepositoryImp;
import service.TwitterService;

public class TwitterServiceImp implements TwitterService {
  private TwitterRepository twitterRepository = TwitterRepositoryImp.getInstance();

  public static TwitterServiceImp instance;

  public static TwitterServiceImp getInstance() {
    if (instance == null)
      instance = new TwitterServiceImp();
    return instance;
  }

  private TwitterServiceImp() {
    twitterRepository.loadData(); 
  }

  @Override
  public List<TwitterModel> getAllModels() {
    return twitterRepository.getAllModels();
  }

  @Override
  public List<TwitterModel> getArticlesByTag(String tag) {
    return twitterRepository.getArticleByTags(tag);
  }

  @Override
  public Map<String, Integer> getTagFrequencyByDay(String day) {
    return twitterRepository.getTagFrequencyByDay(day);
  }

  @Override
  public Map<String, Integer> getTagFrequencyByMonth(String month) {
    return twitterRepository.getTagFrequencyByMonth(month);
  }

  public List<TwitterModel> addFavorite(String title) {
    return twitterRepository.addFavorite(title);
  }

  public List<TwitterModel> removeFavorite(String title) {
    return twitterRepository.removeFavorite(title);
  }
}