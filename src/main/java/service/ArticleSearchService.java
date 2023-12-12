package service;

import java.util.ArrayList;
import java.util.List;

import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class ArticleSearchService {
    
    public static List<String> searchArticlesByTitle(String title) {
        List<String> matchingArticles = new ArrayList<>();
        
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        
        matchingArticles.addAll(blogService.getArticleByTitle(title));
        matchingArticles.addAll(coinDeskService.getArticleByTitle(title));
        matchingArticles.addAll(cryptoNewsService.getArticleByTitle(title));
        matchingArticles.addAll(artNewsService.getArticleByTitle(title));

        return matchingArticles;
    }

    public static void main(String[] args) {
        String title = " succeed in the race"; // Điều chỉnh tiêu đề  muốn tìm kiếm
        List<String> matchingArticles = searchArticlesByTitle(title);
        if (!matchingArticles.isEmpty()) {
            System.out.println("Matching articles:");
            matchingArticles.forEach(System.out::println);
        } else {
            System.out.println("No matching articles found.");
        }
    }
}
