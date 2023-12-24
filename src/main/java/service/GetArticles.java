package service;

import java.util.ArrayList;
import java.util.List;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.PlazaNFTServiceImp;
import service.implement.TheartNewsPaperServiceImp;
import service.implement.TwitterServiceImp;
import models.BlogModel;

public class GetArticles {

  private static BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
  private static CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
  private static CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
  private static TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
  private static PlazaNFTServiceImp plazaNFTService = PlazaNFTServiceImp.getInstance();
  private static TwitterServiceImp twitterService = TwitterServiceImp.getInstance();

  public static List<BlogModel> allArticles() {
    List<BlogModel> allArticles = new ArrayList<>();

    allArticles.addAll(blogService.getAllModels());
    allArticles.addAll(coinDeskService.getAllCoin());
    allArticles.addAll(cryptoNewsService.getAllModels());
    allArticles.addAll(artNewsService.getAllModels());
    allArticles.addAll(plazaNFTService.getAllModels());
    allArticles.addAll(twitterService.getAllModels());
    return allArticles;
  }
}
