package service;

import java.util.ArrayList;
import java.util.List;

import models.BlogModel;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class ArticleSearchService {
    
    public static List<BlogModel> searchArticlesByTag(String tag) {
        List<BlogModel> matchingArticles = new ArrayList<>();
        
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();;

        matchingArticles.addAll(blogService.getArticlesByTag(tag));
        matchingArticles.addAll(coinDeskService.getArticlesByTag(tag));
        matchingArticles.addAll(cryptoNewsService.getArticlesByTag(tag));
        matchingArticles.addAll(artNewsService.getArticlesByTag(tag));
        
        return matchingArticles;
    }
    
    public static void main(String[] args) {
        String tag = "NFT"; // Điều chỉnh tiêu đề  muốn tìm kiếm
        List<BlogModel> matchingArticles = searchArticlesByTag(tag);
        if (!matchingArticles.isEmpty()) {
            System.out.println("Matching articles:");
            matchingArticles.forEach(System.out::println);
        } else {
            System.out.println("No matching articles found.");
        }
    }
}
