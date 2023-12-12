package service;

import java.util.ArrayList;
import java.util.List;

import repository.implement.NiftyGateWayApiCallRepositoryImp;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.NiftyGateWayApicallServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class ArticleSearchService {
    
    public static List<Object> searchArticlesByTitle(String title) {
        List<Object> matchingArticles = new ArrayList<>();
        
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        NiftyGateWayApicallServiceImp niftyNewsService = NiftyGateWayApicallServiceImp.getInstance();
        
        matchingArticles.addAll(blogService.getArticlesByTitle(title));
        matchingArticles.addAll(coinDeskService.getArticlesByTitle(title));
        matchingArticles.addAll(cryptoNewsService.getArticlesByTitle(title));
        matchingArticles.addAll(artNewsService.getArticlesByTitle(title));
        matchingArticles.addAll(niftyNewsService.getArticlesByTitle(title));
        
        return matchingArticles;
    }
    
    public static void main(String[] args) {
        String title = "AFRODITE"; // Điều chỉnh tiêu đề  muốn tìm kiếm
        List<Object> matchingArticles = searchArticlesByTitle(title);
        if (!matchingArticles.isEmpty()) {
            System.out.println("Matching articles:");
            matchingArticles.forEach(System.out::println);
        } else {
            System.out.println("No matching articles found.");
        }
    }
}
