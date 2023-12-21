package service;

import java.util.List;

import models.EbisuBayModel;

public interface EbisuBayService {
    List<EbisuBayModel> getAllModels();
    List<EbisuBayModel> findModelsByName(String name);
}
