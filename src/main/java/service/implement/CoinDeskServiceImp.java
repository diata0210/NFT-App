package service.implement;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;
import models.CoinDeskBlogModel;
import repository.CoinDeskRepository;
import repository.implement.CoinDeskRepositoryImp;
import service.CoinDeskService;

public class CoinDeskServiceImp implements CoinDeskService {
    public static CoinDeskServiceImp instance;
    private CoinDeskRepository coinDeskRepository = CoinDeskRepositoryImp.getInstance();

    public static CoinDeskServiceImp getInstance() {
        if (instance == null)
            instance = new CoinDeskServiceImp();

        return instance;
    }

    private CoinDeskServiceImp() {
        coinDeskRepository.loadData(); // Gọi loadData ở đây
    }

    @Override
    public List<CoinDeskBlogModel> getAllCoin() {
        return coinDeskRepository.getAllCoin();
    }

    @Override
    public List<CoinDeskBlogModel> getArticlesByTag(String tag) {
        return coinDeskRepository.getArticlesByTag(tag);
    }

    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        return coinDeskRepository.getTagFrequencyByMonth(month);
    }

    @Override
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        return coinDeskRepository.getTagFrequencyByDay(day);
    }

    public List<CoinDeskBlogModel> addFavorite(String title) {
        return coinDeskRepository.addFavorite(title);
    }

    public List<CoinDeskBlogModel> removeFavorite(String title) {
        return coinDeskRepository.removeFavorite(title);
    }

    public static void main(String[] args) {

    }
}
