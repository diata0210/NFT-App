package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CoinDeskBlogModel;
import repository.CoinDeskRepository;
import repository.Repository;

public class CoinDeskRepositoryImp implements CoinDeskRepository, Repository {
    public static CoinDeskRepositoryImp instance;
    private List<CoinDeskBlogModel> models = new ArrayList<>();
    public static CoinDeskRepositoryImp getInstance() {
        if (instance == null)
            instance = new CoinDeskRepositoryImp();
        return instance;
    }
    @Override
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CoinDeskBlogModel[] sites = mapper.readValue(new File(JsonURL.COINDESK), CoinDeskBlogModel[].class);
            for (CoinDeskBlogModel site : sites)
                models.add(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<CoinDeskBlogModel> getAllCoin() {
        return models;
    }
    @Override
    public List<String> getArticleByTags(String tag) {
        List<String> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase(); 
        for (CoinDeskBlogModel model : models) {
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
        CoinDeskRepositoryImp mod = new CoinDeskRepositoryImp();
        mod.loadData();
        for (String md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
