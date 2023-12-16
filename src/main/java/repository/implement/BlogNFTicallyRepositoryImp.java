package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.BlogNFTicallyModel;
import repository.BlogNFTicallyRepository;
import repository.Repository;

public class BlogNFTicallyRepositoryImp implements BlogNFTicallyRepository, Repository {
    public static BlogNFTicallyRepositoryImp instance;
    private List<BlogNFTicallyModel> models = new ArrayList<>();
    private List<BlogNFTicallyModel> favoriteArticles = new ArrayList<>(); // Array cac bai viet duoc yeu thich

    public static BlogNFTicallyRepositoryImp getInstance() {
        if (instance == null)
            instance = new BlogNFTicallyRepositoryImp();
        return instance;
    }

    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BlogNFTicallyModel[] sites = mapper.readValue(new File(JsonURL.NFTICALLY), BlogNFTicallyModel[].class);
            for (BlogNFTicallyModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<BlogNFTicallyModel> getAllModels() {
        return models;
    }

    @Override
    public List<BlogNFTicallyModel> getArticleByTags(String tag) {
        List<BlogNFTicallyModel> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (BlogNFTicallyModel model : models) {
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
        for (BlogNFTicallyModel model : models) {
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
        for (BlogNFTicallyModel model : models) {
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

    public List<BlogNFTicallyModel> addFavorite(String title) {
        for (BlogNFTicallyModel model : models) {
            if (model.getTitle().equalsIgnoreCase(title) && !favoriteArticles.contains(model)) {
                favoriteArticles.add(model);
                break;
            }
        }
        return new ArrayList<>(favoriteArticles);
    }

    public List<BlogNFTicallyModel> removeFavorite(String title) {
        favoriteArticles.removeIf(article -> article.getTitle().equalsIgnoreCase(title));
        return new ArrayList<>(favoriteArticles);
    }

    public static void main(String[] args) {
        BlogNFTicallyRepositoryImp mod = new BlogNFTicallyRepositoryImp();
        mod.loadData();
        for (BlogNFTicallyModel md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
