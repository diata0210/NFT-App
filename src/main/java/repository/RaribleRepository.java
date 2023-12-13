package repository;

import java.util.List;

import models.RaribleModel;

public interface RaribleRepository {
    List<RaribleModel> getAllModels();
    List<RaribleModel> findModelsByName(String name);
    
}
