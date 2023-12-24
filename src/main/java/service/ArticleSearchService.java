package service;

import java.util.ArrayList;
import java.util.List;

import models.BlogModel;
import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class ArticleSearchService {
  private static BlogNFTicallyService blogService = BlogNFTicallyServiceImp.getInstance();
  private static CoinDeskService coinDeskService = CoinDeskServiceImp.getInstance();
  private static CrytoNewsBlogService cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
  private static TheartNewsPaperService artNewsService = TheartNewsPaperServiceImp.getInstance();

  public static List<BlogModel> searchArticlesByTag(String tag) {
    List<BlogModel> matchingArticles = new ArrayList<>();

    matchingArticles.addAll(blogService.getArticlesByTag(tag));
    matchingArticles.addAll(coinDeskService.getArticlesByTag(tag));
    matchingArticles.addAll(cryptoNewsService.getArticlesByTag(tag));
    matchingArticles.addAll(artNewsService.getArticlesByTag(tag));

    return matchingArticles;
  }
}
