package service;

import java.util.ArrayList;
import java.util.List;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.PlazaNFTServiceImp;
import service.implement.TheartNewsPaperServiceImp;
import service.implement.TwitterServiceImp;

public class addFavouriteArticle {
    
    public static List<Object> addFavouriteArticle(String title) {
        List<Object> favouriteArticles = new ArrayList<>();
        
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        TwitterServiceImp twitterService  = TwitterServiceImp.getInstance();
        PlazaNFTServiceImp plazaNFTService = PlazaNFTServiceImp.getInstance();
        favouriteArticles.addAll(blogService.addFavorite(title));
        favouriteArticles.addAll(coinDeskService.addFavorite(title));
        favouriteArticles.addAll(cryptoNewsService.addFavorite(title));
        favouriteArticles.addAll(artNewsService.addFavorite(title));
        favouriteArticles.addAll(plazaNFTService.addFavorite(title));
        //favouriteArticles.addAll(twitterService.addFavorite(title));
        return favouriteArticles;
    }
    
    public static void main(String[] args) {
        String title = "Web3 : How Ai is changing it - NFTICALLY"; // Adjust the title to be searched
        List<Object> favouriteArticles =  addFavouriteArticle(title);

        if (!favouriteArticles.isEmpty()) {
            System.out.println("Added articles to favorites:");
            favouriteArticles.forEach(System.out::println);
        } else {
            System.out.println("No articles found to add to favorites.");
        }
    }
}
