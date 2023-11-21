package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
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
            TheartNewPaperBlogModel[] sites = mapper.readValue(new File(JsonURL.ARTPAPER),TheartNewPaperBlogModel[].class);
            for (TheartNewPaperBlogModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TheartNewPaperBlogModel> getAllCoin() {
        return models;
    }

    @Override
    public List<String> getArticleByTags(String tag) {
        List<String> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase(); 
        for (TheartNewPaperBlogModel model : models) {
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

    public static void main(String[] args) {
       TheartNewsPaperRepositoryImp mod = new TheartNewsPaperRepositoryImp();
        mod.loadData();
        for (String md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
