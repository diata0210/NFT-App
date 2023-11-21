package repository.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.BlogNFTicallyModel;
import models.CoinDeskBlogModel;
import repository.CoinDeskRepository;
import repository.BlogNFTicallyRepository;
import repository.Repository;

public class BlogNFTicallyRepositorylmp implements BlogNFTicallyRepository, Repository {
    public static BlogNFTicallyRepositorylmp instance;
    private List<BlogNFTicallyModel> models = new ArrayList<>();

    public static BlogNFTicallyRepositorylmp getInstance() {
        if (instance == null)
            instance = new BlogNFTicallyRepositorylmp();
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
    public List<BlogNFTicallyModel> getAllCoin() {
        return models;
    }

    @Override
    public List<String> getArticleByTags(String tag) {
        List<String> allArticles = new ArrayList<>();
        String lowercaseTag = tag.toLowerCase(); 
        for (BlogNFTicallyModel model : models) {
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
       BlogNFTicallyRepositorylmp mod = new BlogNFTicallyRepositorylmp();
        mod.loadData();
        for (String md : mod.getArticleByTags("NFTS")) {
            System.out.println(md);
        }
    }
}
