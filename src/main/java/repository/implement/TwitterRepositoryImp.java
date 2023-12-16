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
import models.TheartNewPaperBlogModel;
import models.TwitterModel;
import repository.TwitterRepository;
import repository.Repository;
import twitter4j.Twitter;

public class TwitterRepositoryImp implements TwitterRepository, Repository {
    public static TwitterRepositoryImp instance;
    private List<TwitterModel> models = new ArrayList<>();
    private List<TwitterModel> favoriteArticles = new ArrayList<>(); // Array cac bai viet duoc yeu thich
    public static TwitterRepositoryImp getInstance() {
        if (instance == null)
            instance = new TwitterRepositoryImp();
        return instance;
    }

    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TwitterModel[] sites = mapper.readValue(new File(JsonURL.TWITTER), TwitterModel[].class);
            for (TwitterModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TwitterModel> getAllModels() {
        return models;
    }

    @Override
    public List<TwitterModel> getArticleByTags(String tag) {
        List<TwitterModel> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (TwitterModel model : models) {
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


    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (TwitterModel model : models) {
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

    public Map<String, Integer> getTagFrequencyByDay(String day) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (TwitterModel model : models) {
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

     public List<TwitterModel> addFavorite(String title) {
        for (TwitterModel model : models) {
            if (model.getTitle().equalsIgnoreCase(title) && !favoriteArticles.contains(model)) {
                favoriteArticles.add(model);
                break;
            }
        }
        return new ArrayList<>(favoriteArticles);
    }

    public List<TwitterModel> removeFavorite(String title) {
        favoriteArticles.removeIf(article -> article.getTitle().equalsIgnoreCase(title));
        return new ArrayList<>(favoriteArticles);
    }

    public static void main(String[] args) {
       TwitterRepositoryImp mod = new TwitterRepositoryImp();
        mod.loadData();
        for (TwitterModel md : mod.getArticleByTags("#NFTs")) {
            System.out.println(md);
        }
    }
}
