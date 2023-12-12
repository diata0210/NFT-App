package service.implement;

import java.util.List;
import java.util.Map;

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

    private BlogNFTicallyServiceImp() {
        blogNFTicallyRepository.loadData(); // Gọi loadData ở đây
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
    public Map<String, Integer> getTagFrequencyByDay(String day) {
        return blogNFTicallyRepository.getTagFrequencyByDay(day);
    }

    public Map<String, Integer> getTagFrequencyByMonth(String month) {
        return blogNFTicallyRepository.getTagFrequencyByMonth(month);
    }

    public List<BlogNFTicallyModel> getArticlesByTitle(String title){
         return blogNFTicallyRepository.getArticlesByTitle(title);
    }

    public static void main(String[] args) {
    }
}
