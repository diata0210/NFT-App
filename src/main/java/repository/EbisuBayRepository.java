package repository;

import java.util.List;

import models.EbisuBayModel;

public interface EbisuBayRepository {
  void loadData();

  List<EbisuBayModel> getAllModels();

  List<EbisuBayModel> findModelsByName(String name);

}
