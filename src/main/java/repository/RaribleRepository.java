package repository;

import java.util.List;

import models.RaribleModel;

public interface RaribleRepository {
  void loadData();

  List<RaribleModel> getAllModels();

  List<RaribleModel> findModelsByName(String name);

}
