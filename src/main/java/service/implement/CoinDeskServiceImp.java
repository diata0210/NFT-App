package service.implement;

import java.util.List;
import java.util.Map;

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
    public List<String> getArticleByTags(String tag) {
        return coinDeskRepository.getArticleByTags(tag);
    }

    @Override
    public List<String> getTagsArticleByMonth(String month) {
        return null;
    }

    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        return coinDeskRepository.getTagFrequencyByMonth(month);
    }

    @Override
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        return coinDeskRepository.getTagFrequencyByDay(day);
    }

    public List<String> getArticleByTitle(String title) {
        return coinDeskRepository.getArticleByTitle(title);
    }

    public static void main(String[] args) {

    }
}
