package service.implement;

import java.util.List;
import java.util.Map;

import models.TheartNewPaperBlogModel;
import repository.TheartNewsPaperRepository;
import repository.implement.TheartNewsPaperRepositoryImp;
import service.TheartNewsPaperService;

public class TheartNewsPaperServiceImp implements TheartNewsPaperService {
  private TheartNewsPaperRepository theartNewsPaperRepository = TheartNewsPaperRepositoryImp.getInstance();

  public static TheartNewsPaperServiceImp instance;

  public static TheartNewsPaperServiceImp getInstance() {
    if (instance == null)
      instance = new TheartNewsPaperServiceImp();
    return instance;
  }

  private TheartNewsPaperServiceImp() {
    theartNewsPaperRepository.loadData(); 
  }

  @Override
  public List<TheartNewPaperBlogModel> getAllModels() {
    return theartNewsPaperRepository.getAllModels();
  }

  @Override
  public List<TheartNewPaperBlogModel> getArticlesByTag(String tag) {
    return theartNewsPaperRepository.getArticleByTags(tag);
  }

  @Override
  public Map<String, Integer> getTagFrequencyByDay(String day) {
    return theartNewsPaperRepository.getTagFrequencyByDay(day);
  }

  @Override
  public Map<String, Integer> getTagFrequencyByMonth(String month) {
    return theartNewsPaperRepository.getTagFrequencyByMonth(month);
  }
}