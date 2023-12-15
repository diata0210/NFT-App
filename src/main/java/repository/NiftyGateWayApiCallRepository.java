package repository;

import java.util.List;

import models.NiftyGateWayApiCallModel;

public interface NiftyGateWayApiCallRepository{
    void loadData();

    List<NiftyGateWayApiCallModel> getAllModels();

    List<String> getNFTsByAuthor(String author);
}
