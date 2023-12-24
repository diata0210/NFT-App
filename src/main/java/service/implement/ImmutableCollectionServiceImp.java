package service.implement;

import java.util.List;

import models.ImmutableCollectionModel;
import repository.ImmutableCollectionRepository;
import repository.implement.ImmutableCollectionRepositoryImp;
import service.ImmutableCollectionService;

public class ImmutableCollectionServiceImp implements ImmutableCollectionService {
  private ImmutableCollectionRepository ImmutableCollectionRepository = ImmutableCollectionRepositoryImp
      .getInstance();

  public static ImmutableCollectionServiceImp instance;

  public static ImmutableCollectionServiceImp getInstance() {
    if (instance == null)
      instance = new ImmutableCollectionServiceImp();
    return instance;
  }

  private ImmutableCollectionServiceImp() {
    ImmutableCollectionRepository.loadData();
  }

  @Override
  public List<ImmutableCollectionModel> findModelsByName(String name) {
    return ImmutableCollectionRepository.findModelsByName(name);
  }

  public List<ImmutableCollectionModel> getAllModels() {
    return ImmutableCollectionRepository.getAllModels();
  }
}
