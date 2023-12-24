package service.implement;

import java.util.List;
import java.util.Map;

import models.CtytoNewsBlogModel;
import repository.CrytoNewsBlogRepository;
import repository.implement.CrytoNewsBlogRepositoryImp;
import service.CrytoNewsBlogService;

public class CrytoNewsBlogServiceImp implements CrytoNewsBlogService {
  private CrytoNewsBlogRepository crytoNewsBlogRepository = CrytoNewsBlogRepositoryImp.getInstance();

  public static CrytoNewsBlogServiceImp instance;

  public static CrytoNewsBlogServiceImp getInstance() {
    if (instance == null)
      instance = new CrytoNewsBlogServiceImp();
    return instance;
  }

  private CrytoNewsBlogServiceImp() {
    crytoNewsBlogRepository.loadData(); 
  }

  @Override
  public List<CtytoNewsBlogModel> getAllModels() {
    return crytoNewsBlogRepository.getAllModels();
  }

  @Override
  public List<CtytoNewsBlogModel> getArticlesByTag(String tag) {
    return crytoNewsBlogRepository.getArticleByTags(tag);
  }

  @Override
  public Map<String, Integer> getTagFrequencyByDay(String day) {
    return crytoNewsBlogRepository.getTagFrequencyByDay(day);
  }

  public Map<String, Integer> getTagFrequencyByMonth(String month) {
    return crytoNewsBlogRepository.getTagFrequencyByMonth(month);
  }
}
