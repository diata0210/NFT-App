package service;

import java.util.List;

import models.RaribleModel;

public interface RaribleService {
  List<RaribleModel> getAllModels();

  List<RaribleModel> findModelsByName(String name);
}
