package service;

import java.util.List;

import models.PlazaNFTModel;

public interface PlazaNFTService {
    List<PlazaNFTModel> getAllModels();

    List<String> getNFTsByTags(String tag);

    List<String> getTagsByDate(String date);

    List<String> getTagsByWeek(String startDate);

    List<String> getTagsByMonth(String month);
}
