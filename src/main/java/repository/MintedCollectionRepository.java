package repository;

import java.util.List;

import models.MintedCollectionModel;

public interface MintedCollectionRepository {
  void loadData();

  List<MintedCollectionModel> getAllModels();

  List<MintedCollectionModel> findModelsByName(String name);

}