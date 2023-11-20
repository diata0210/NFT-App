package service.implement;

import java.util.List;

import models.CoinDeskBlogModel;
import repository.CoinDeskRepository;
import repository.implement.CoinDeskRepositoryImp;
import service.CoinDeskService;

public class CoinDeskServiceImp implements CoinDeskService {
    public static CoinDeskServiceImp instance;
    private CoinDeskRepository coinDeskRepository = CoinDeskRepositoryImp.getInstance();

    public static CoinDeskServiceImp getInstance(){
        if(instance == null) instance = new CoinDeskServiceImp();
        return instance;
    }

    @Override
    public List<CoinDeskBlogModel> getAllCoin() {
        return coinDeskRepository.getAllCoin();
    }

    @Override
    public List<String> getArticleByTags(String tag) {
        return coinDeskRepository.getArticleByTags(tag);
    }

    @Override
    public List<String> getTagsArticleByDay(String date) {
        return null;
    }
    
    @Override
    public List<String> getTagsArticleByWeek(String startDate) {
        return null;
    }

    @Override
    public List<String> getTagsArticleByMonth(String month) {
        return null;
    }

    public static void main(String[] args) {

    }
}
