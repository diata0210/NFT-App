package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CtytoNewsBlogModel;
import models.PlazaNFTModel;
import repository.PlazaNFTRepository;
import repository.Repository;

public class PlazaNFTRepositoryImp implements PlazaNFTRepository, Repository {
    public static PlazaNFTRepositoryImp instance;
    private List<PlazaNFTModel> models = new ArrayList<>();
    private List<PlazaNFTModel> favoriteArticles = new ArrayList<>(); // Array cac bai viet duoc yeu thich
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
    public List<PlazaNFTModel> getNFTsByTags(String tag) {
        List<PlazaNFTModel> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (PlazaNFTModel model : models) {
            List<String> lowercaseTags = model.getRelatedTags().stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (lowercaseTags.contains(lowercaseTag)) {
                allArticles.add(model);
                System.out.println(model.getTitle());
            }
        }
        return allArticles;
    }

    

     @Override
    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (PlazaNFTModel model : models) {
            String date = model.getDate();
            // Kiểm tra để đảm bảo rằng chuỗi ngày không rỗng và có độ dài phù hợp
            if (date != null && date.length() == 10) {
                String modelMonth = date.substring(5, 7); // Lấy phần tháng từ chuỗi ngày
                if (modelMonth.equals(month)) {
                    for (String tag : model.getRelatedTags()) {
                        tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                    }
                }
            }
        }
        return tagFrequency;
    }

    @Override
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (PlazaNFTModel model : models) {
            String date = model.getDate();
            // Kiểm tra để đảm bảo rằng chuỗi ngày không rỗng và có độ dài phù hợp
            if (date != null && date.length() == 10) {
                String modelDay = date.substring(5, 10); // Lấy phần tháng từ chuỗi ngày
                if (modelDay.equals(day)) {
                    for (String tag : model.getRelatedTags()) {
                        tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                    }
                }
            }
        }
        return tagFrequency;
    }
       public List<PlazaNFTModel> addFavorite(String title) {
        for (PlazaNFTModel model : models) {
            if (model.getTitle().equalsIgnoreCase(title) && !favoriteArticles.contains(model)) {
                favoriteArticles.add(model);
                break;
            }
        }
        return new ArrayList<>(favoriteArticles);
    }

    public List<PlazaNFTModel> removeFavorite(String title) {
        favoriteArticles.removeIf(article -> article.getTitle().equalsIgnoreCase(title));
        return new ArrayList<>(favoriteArticles);
    }

    public static void main(String[] args) {
        PlazaNFTRepositoryImp mod = new PlazaNFTRepositoryImp();
        mod.loadData();
        for (PlazaNFTModel md : mod.getNFTsByTags("NFTS")) {
            System.out.println(md);
        }
    }
}