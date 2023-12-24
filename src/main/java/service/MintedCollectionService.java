package service;

import java.util.List;

import models.MintedCollectionModel;

public interface MintedCollectionService {
  List<MintedCollectionModel> getAllModels();

  List<MintedCollectionModel> findModelsByName(String name);
}
