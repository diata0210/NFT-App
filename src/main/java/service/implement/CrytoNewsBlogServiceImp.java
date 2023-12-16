package service.implement;

import java.util.List;
import java.util.Map;

import models.CoinDeskBlogModel;
import models.CtytoNewsBlogModel;
import repository.CrytoNewsBlogRepository;
import repository.implement.CrytoNewsBlogRepositoryImp;
import service.CtytoNewsBlogService;

public class CrytoNewsBlogServiceImp implements CtytoNewsBlogService {
    private CrytoNewsBlogRepository crytoNewsBlogRepository = CrytoNewsBlogRepositoryImp.getInstance();

    public static CrytoNewsBlogServiceImp instance;

    public static CrytoNewsBlogServiceImp getInstance() {
        if (instance == null)
            instance = new CrytoNewsBlogServiceImp();
        return instance;
    }

    private CrytoNewsBlogServiceImp() {
        crytoNewsBlogRepository.loadData(); // Gọi loadData ở đây
    }

    @Override
    public List<CtytoNewsBlogModel> getAllModels() {
        return crytoNewsBlogRepository.getAllModels();
    }

    @Override
    public List<CtytoNewsBlogModel> getArticlesByTag(String tag) {
        return crytoNewsBlogRepository.getArticleByTags(tag);
    }

    @Override
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        return crytoNewsBlogRepository.getTagFrequencyByDay(day);
    }

    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        return crytoNewsBlogRepository.getTagFrequencyByMonth(month);
    }
      public List<CtytoNewsBlogModel> addFavorite(String title) {
        return crytoNewsBlogRepository.addFavorite(title);
    }

    public List<CtytoNewsBlogModel> removeFavorite(String title) {
        return crytoNewsBlogRepository.removeFavorite(title);
    }
    public static void main(String[] args) {

    }
}
