package service.implement;

import java.util.List;

import models.TheartNewPaperBlogModel;
import repository.TheartNewsPaperRepository;
import repository.implement.TheartNewsPaperRepositoryImp;
import service.TheartNewsPaperService;

public class TheartNewsPaperServiceImp implements TheartNewsPaperService {
    private TheartNewsPaperRepository theartNewsPaperRepository = TheartNewsPaperRepositoryImp.getInstance();

    public static TheartNewsPaperServiceImp instance;

    public static TheartNewsPaperServiceImp getInstance() {
        if (instance == null)
            instance = new TheartNewsPaperServiceImp();
        return instance;
    }

    @Override
    public List<TheartNewPaperBlogModel> getAllModels() {
        return theartNewsPaperRepository.getAllModels();
    }

    @Override
    public List<String> getArticlesByTag(String tag) {
        return theartNewsPaperRepository.getArticleByTags(tag);
    }

    @Override
    public List<String> getArticlesByDay(String date) {
        return theartNewsPaperRepository.getTagsArticleByDay(date);
    }

    @Override
    public List<String> getArticlesByWeek(String startDate) {
        return theartNewsPaperRepository.getTagsArticleByWeek(startDate);
    }

    @Override
    public List<String> getArticlesByMonth(String month) {
        return theartNewsPaperRepository.getTagsArticleByMonth(month);
    }

    public static void main(String[] args) {

    }
}
