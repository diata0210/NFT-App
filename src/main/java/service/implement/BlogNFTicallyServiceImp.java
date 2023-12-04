package service.implement;

import java.util.List;

import models.BlogNFTicallyModel;
import repository.BlogNFTicallyRepository;
import repository.implement.BlogNFTicallyRepositoryImp;
import service.BlogNFTicallyService;

public class BlogNFTicallyServiceImp implements BlogNFTicallyService {
    private BlogNFTicallyRepository blogNFTicallyRepository = BlogNFTicallyRepositoryImp.getInstance();

    public static BlogNFTicallyServiceImp instance;

    public static BlogNFTicallyServiceImp getInstance() {
        if (instance == null)
            instance = new BlogNFTicallyServiceImp();
        return instance;
    }

    @Override
    public List<BlogNFTicallyModel> getAllModels() {
        return blogNFTicallyRepository.getAllModels();
    }

    @Override
    public List<String> getArticlesByTag(String tag) {
        return blogNFTicallyRepository.getArticleByTags(tag);
    }

    @Override
    public List<String> getArticlesByDay(String date) {
        return blogNFTicallyRepository.getTagsArticleByDay(date);
    }

    @Override
    public List<String> getArticlesByWeek(String startDate) {
        return blogNFTicallyRepository.getTagsArticleByWeek(startDate);
    }

    @Override
    public List<String> getArticlesByMonth(String month) {
        return blogNFTicallyRepository.getTagsArticleByMonth(month);
    }

    public static void main(String[] args) {

    }
}
