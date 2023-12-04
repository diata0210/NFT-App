package service.implement;

import java.util.List;

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

    @Override
    public List<CtytoNewsBlogModel> getAllModels() {
        return crytoNewsBlogRepository.getAllModels();
    }

    @Override
    public List<String> getArticlesByTag(String tag) {
        return crytoNewsBlogRepository.getArticleByTags(tag);
    }

    @Override
    public List<String> getArticlesByDay(String date) {
        return crytoNewsBlogRepository.getTagsArticleByDay(date);
    }

    @Override
    public List<String> getArticlesByWeek(String startDate) {
        return crytoNewsBlogRepository.getTagsArticleByWeek(startDate);
    }

    @Override
    public List<String> getArticlesByMonth(String month) {
        return crytoNewsBlogRepository.getTagsArticleByMonth(month);
    }

    public static void main(String[] args) {

    }
}
