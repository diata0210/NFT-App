package service.implement;

import java.util.List;

import models.RaribleModel;
import repository.RaribleRepository;
import repository.implement.RaribleRepositoryImp;
import service.RaribleService;

public class RaribleServiceImp implements RaribleService {
  private RaribleRepository raribleRepository = RaribleRepositoryImp.getInstance();

  public static RaribleServiceImp instance;

  public static RaribleServiceImp getInstance() {
    if (instance == null)
      instance = new RaribleServiceImp();
    return instance;
  }

  private RaribleServiceImp() {
    raribleRepository.loadData(); 
  }

  @Override
  public List<RaribleModel> findModelsByName(String name) {
    return raribleRepository.findModelsByName(name);
  }

  public List<RaribleModel> getAllModels() {
    return raribleRepository.getAllModels();
  }

}
