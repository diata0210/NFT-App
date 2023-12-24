package service.implement;

import java.util.List;

import models.EbisuBayModel;
import repository.EbisuBayRepository;
import repository.implement.EbisuBayRepositoryImp;
import service.EbisuBayService;

public class EbisuBayServiceImp implements EbisuBayService {
  private EbisuBayRepository EbisuBayRepository = EbisuBayRepositoryImp.getInstance();

  public static EbisuBayServiceImp instance;

  public static EbisuBayServiceImp getInstance() {
    if (instance == null)
      instance = new EbisuBayServiceImp();
    return instance;
  }

  private EbisuBayServiceImp() {
    EbisuBayRepository.loadData(); 
  }

  @Override
  public List<EbisuBayModel> findModelsByName(String name) {
    return EbisuBayRepository.findModelsByName(name);
  }

  public List<EbisuBayModel> getAllModels() {
    return EbisuBayRepository.getAllModels();
  }
}
