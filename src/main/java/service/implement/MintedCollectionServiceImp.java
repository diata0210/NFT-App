package service.implement;

import java.util.List;

import models.MintedCollectionModel;
import repository.MintedCollectionRepository;
import repository.implement.MintedCollectionRepositoryImp;
import service.MintedCollectionService;

public class MintedCollectionServiceImp implements MintedCollectionService {
  private MintedCollectionRepository MintedCollectionRepository = MintedCollectionRepositoryImp.getInstance();

  public static MintedCollectionServiceImp instance;

  public static MintedCollectionServiceImp getInstance() {
    if (instance == null)
      instance = new MintedCollectionServiceImp();
    return instance;
  }

  private MintedCollectionServiceImp() {
    MintedCollectionRepository.loadData(); 
  }

  @Override
  public List<MintedCollectionModel> findModelsByName(String name) {
    return MintedCollectionRepository.findModelsByName(name);
  }

  public List<MintedCollectionModel> getAllModels() {
    return MintedCollectionRepository.getAllModels();
  }
}
