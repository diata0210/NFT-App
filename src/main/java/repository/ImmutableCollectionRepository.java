package repository;

import java.util.List;

import models.ImmutableCollectionModel;

public interface ImmutableCollectionRepository {
  void loadData();

  List<ImmutableCollectionModel> getAllModels();

  List<ImmutableCollectionModel> findModelsByName(String name);

}