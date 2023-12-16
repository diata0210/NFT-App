  package service;

import java.util.ArrayList;
import java.util.List;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.PlazaNFTServiceImp;
import service.implement.TheartNewsPaperServiceImp;
import service.implement.TwitterServiceImp;

public class ArticleSearchService {
    
    public static List<Object> searchArticlesByTag(String tag) {
        List<Object> matchingArticles = new ArrayList<>();
        
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        TwitterServiceImp twitterService  = TwitterServiceImp.getInstance();
        PlazaNFTServiceImp plazaNFTService = PlazaNFTServiceImp.getInstance();
        matchingArticles.addAll(blogService.getArticlesByTag(tag));
        matchingArticles.addAll(coinDeskService.getArticlesByTag(tag));
        matchingArticles.addAll(cryptoNewsService.getArticlesByTag(tag));
        matchingArticles.addAll(artNewsService.getArticlesByTag(tag));
        matchingArticles.addAll(twitterService.getArticlesByTag(tag));
        matchingArticles.addAll(plazaNFTService.getNFTsByTags(tag));
        return matchingArticles;
    }
    
    public static void main(String[] args) {
        String tag = "#NFT"; // Điều chỉnh tiêu đề  muốn tìm kiếm
        List<Object> matchingArticles = searchArticlesByTag(tag);
        if (!matchingArticles.isEmpty()) {
            System.out.println("Matching articles:");
            matchingArticles.forEach(System.out::println);
        } else {
            System.out.println("No matching articles found.");
        }
    }
}
