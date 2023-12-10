package service;

import java.util.List;
import java.util.Map;

import models.PlazaNFTModel;

public interface PlazaNFTService {
    List<PlazaNFTModel> getAllModels();

    List<String> getNFTsByTags(String tag);

    
    Map<String, Integer> getTagFrequencyByMonth(String month);
}
