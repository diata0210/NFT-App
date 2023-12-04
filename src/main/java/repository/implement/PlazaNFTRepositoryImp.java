package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.PlazaNFTModel;
import repository.PlazaNFTRepository;
import repository.Repository;

public class PlazaNFTRepositoryImp implements PlazaNFTRepository, Repository {
    public static PlazaNFTRepositoryImp instance;
    private List<PlazaNFTModel> models = new ArrayList<>();

    public static PlazaNFTRepositoryImp getInstance() {
        if (instance == null)
            instance = new PlazaNFTRepositoryImp();
        return instance;
    }

    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PlazaNFTModel[] sites = mapper.readValue(new File(JsonURL.PLAZANFT), PlazaNFTModel[].class);
            for (PlazaNFTModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlazaNFTModel> getAllModels() {
        return models;
    }

    @Override
    public List<String> getNFTsByTags(String tag) {
        List<String> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (PlazaNFTModel model : models) {
            List<String> lowercaseTags = model.getRelatedTags().stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (lowercaseTags.contains(lowercaseTag)) {
                allArticles.add(model.getTitle());
                System.out.println(model.getTitle());
            }
        }
        return allArticles;
    }

    @Override
    public List<String> getTagsByDate(String date) {
        // Implementation for retrieving tags by day
        return null;
    }

    @Override
    public List<String> getTagsByWeek(String startDate) {
        // Implementation for retrieving tags by week
        return null;
    }

    @Override
    public List<String> getTagsByMonth(String month) {
        // Implementation for retrieving tags by month
        return null;
    }

    @Override
    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        // Implementation for tag frequency by month
        return null;
    }

    @Override
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        // Implementation for tag frequency by day
        return null;
    }

    public static void main(String[] args) {
        PlazaNFTRepositoryImp mod = new PlazaNFTRepositoryImp();
        mod.loadData();
        for (String md : mod.getNFTsByTags("NFTS")) {
            System.out.println(md);
        }
    }
}