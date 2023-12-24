package service.implement;

import java.util.List;
import java.util.Map;

import models.PlazaNFTModel;
import repository.PlazaNFTRepository;
import repository.implement.PlazaNFTRepositoryImp;
import service.PlazaNFTService;

public class PlazaNFTServiceImp implements PlazaNFTService {
  private static PlazaNFTServiceImp instance;
  private PlazaNFTRepository plazaNFTRepository = PlazaNFTRepositoryImp.getInstance();

  public static PlazaNFTServiceImp getInstance() {
    if (instance == null)
      instance = new PlazaNFTServiceImp();
    return instance;
  }

  @Override
  public List<PlazaNFTModel> getAllModels() {
    return plazaNFTRepository.getAllModels();
  }

  @Override
  public List<PlazaNFTModel> getNFTsByTags(String tag) {
    return plazaNFTRepository.getNFTsByTags(tag);
  }

  public Map<String, Integer> getTagFrequencyByMonth(String month) {
    return plazaNFTRepository.getTagFrequencyByMonth(month);
  }

  public Map<String, Integer> getTagFrequencyByDay(String day) {
    return plazaNFTRepository.getTagFrequencyByDay(day);
  }
}
