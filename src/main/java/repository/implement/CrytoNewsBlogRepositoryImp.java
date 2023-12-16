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
import repository.CrytoNewsBlogRepository;
import repository.Repository;

public class CrytoNewsBlogRepositoryImp implements CrytoNewsBlogRepository, Repository {
    public static CrytoNewsBlogRepositoryImp instance;
    private List<CtytoNewsBlogModel> models = new ArrayList<>();
    private List<CtytoNewsBlogModel> favoriteArticles = new ArrayList<>(); // Array cac bai viet duoc yeu thich

    public static CrytoNewsBlogRepositoryImp getInstance() {
        if (instance == null)
            instance = new CrytoNewsBlogRepositoryImp();
        return instance;
    }

    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CtytoNewsBlogModel[] sites = mapper.readValue(new File(JsonURL.CRYTONEWS), CtytoNewsBlogModel[].class);
            for (CtytoNewsBlogModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CtytoNewsBlogModel> getAllModels() {
        return models;
    }

    @Override
    public List<CtytoNewsBlogModel> getArticleByTags(String tag) {
        List<CtytoNewsBlogModel> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (CtytoNewsBlogModel model : models) {
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
        for (CtytoNewsBlogModel model : models) {
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
        for (CtytoNewsBlogModel model : models) {
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

    public List<CtytoNewsBlogModel> getArticlesByTitle(String title) {
        List<CtytoNewsBlogModel> matchingArticles = new ArrayList<>();
        for (CtytoNewsBlogModel model : models) {
            if (model.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingArticles.add(model);
            }
        }
        return matchingArticles;
    }

    public List<CtytoNewsBlogModel> addFavorite(String title) {
        for (CtytoNewsBlogModel model : models) {
            if (model.getTitle().equalsIgnoreCase(title) && !favoriteArticles.contains(model)) {
                favoriteArticles.add(model);
                break;
            }
        }
        return new ArrayList<>(favoriteArticles);
    }

    public List<CtytoNewsBlogModel> removeFavorite(String title) {
        favoriteArticles.removeIf(article -> article.getTitle().equalsIgnoreCase(title));
        return new ArrayList<>(favoriteArticles);
    }

    public static void main(String[] args) {
        CrytoNewsBlogRepositoryImp mod = new CrytoNewsBlogRepositoryImp();
        mod.loadData();
        for (CtytoNewsBlogModel md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
