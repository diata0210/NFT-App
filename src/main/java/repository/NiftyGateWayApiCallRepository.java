package repository;

import java.util.List;
import java.util.Map;

import models.NiftyGateWayApiCallModel;

public interface NiftyGateWayApiCallRepository{
    void loadData();

    List<NiftyGateWayApiCallModel> getAllModels();

    List<String> getNFTsByAuthor(String author);
    List<String> getArticleByTitle(String title);
}
