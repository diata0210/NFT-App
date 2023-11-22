package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
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
    public List<CtytoNewsBlogModel> getAllCoin() {
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

    public static void main(String[] args) {
       CrytoNewsBlogRepositoryImp mod = new CrytoNewsBlogRepositoryImp();
        mod.loadData();
        for (String md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
