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
import models.CoinDeskBlogModel;
import models.CtytoNewsBlogModel;
import repository.CoinDeskRepository;
import repository.CrytoNewsBlogRepository;
import repository.Repository;

public class CrytoNewsBlogRepositoryImp implements CrytoNewsBlogRepository, Repository {
    public static CrytoNewsBlogRepositoryImp instance;
    private List<CtytoNewsBlogModel> models = new ArrayList<>();

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
    public List<String> getArticleByTags(String tag) {
        List<String> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (CtytoNewsBlogModel model : models) {
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
    public List<String> getTagsArticleByDay(String date) {
        return null;
    }

    public List<String> getTagsArticleByWeek(String startDate) {
        return null;
    }

    public List<String> getTagsArticleByMonth(String month) {
        return null;
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

    public static void main(String[] args) {
        CrytoNewsBlogRepositoryImp mod = new CrytoNewsBlogRepositoryImp();
        mod.loadData();
        for (String md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
