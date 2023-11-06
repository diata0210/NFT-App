package models.service.implement;

import models.CoinDeskBlogModel;
import models.repository.JsonDataRepository;
import models.service.BlogService;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    private final JsonDataRepository jsonDataRepository;

    public BlogServiceImpl(JsonDataRepository jsonDataRepository) {
        this.jsonDataRepository = jsonDataRepository;
    }

    @Override
    public List<CoinDeskBlogModel> getAllBlogData() {
        return jsonDataRepository.getAllBlogData();
    }
}
