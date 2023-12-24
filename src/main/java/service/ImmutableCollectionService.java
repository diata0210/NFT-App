package service;

import java.util.List;

import models.ImmutableCollectionModel;

public interface ImmutableCollectionService {
  List<ImmutableCollectionModel> getAllModels();

  List<ImmutableCollectionModel> findModelsByName(String name);
}
