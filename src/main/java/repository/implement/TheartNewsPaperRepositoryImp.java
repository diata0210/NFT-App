package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CoinDeskBlogModel;
import models.CtytoNewsBlogModel;
import models.PlazaNFTModel;
import models.TheartNewPaperBlogModel;
import repository.TheartNewsPaperRepository;
import repository.Repository;

public class TheartNewsPaperRepositoryImp implements TheartNewsPaperRepository, Repository {
    public static TheartNewsPaperRepositoryImp instance;
    private List<TheartNewPaperBlogModel> models = new ArrayList<>();

    public static TheartNewsPaperRepositoryImp getInstance() {
        if (instance == null)
            instance = new TheartNewsPaperRepositoryImp();
        return instance;
    }

    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TheartNewPaperBlogModel[] sites = mapper.readValue(new File(JsonURL.ARTNEWSPAPER),
                    TheartNewPaperBlogModel[].class);
            for (TheartNewPaperBlogModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TheartNewPaperBlogModel> getAllModels() {
        return models;
    }

    @Override
    public List<TheartNewPaperBlogModel> getArticleByTags(String tag) {
        List<TheartNewPaperBlogModel> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase();
        for (TheartNewPaperBlogModel model : models) {
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
        for (TheartNewPaperBlogModel model : models) {
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
        for (TheartNewPaperBlogModel model : models) {
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
public List<TheartNewPaperBlogModel> getArticlesByTitle(String title) {
        List<TheartNewPaperBlogModel> matchingArticles = new ArrayList<>();
        for (TheartNewPaperBlogModel model : models) {
            if (model.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingArticles.add(model);
            }
        }
        return matchingArticles;
    }
    public static void main(String[] args) {
        TheartNewsPaperRepositoryImp mod = new TheartNewsPaperRepositoryImp();
        mod.loadData();
        for (TheartNewPaperBlogModel md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
