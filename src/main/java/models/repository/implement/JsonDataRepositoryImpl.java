package models.repository.implement;

import models.CoinDeskBlogModel;
import models.repository.JsonDataRepository;

import java.util.Collections;
import java.util.List;

public class JsonDataRepositoryImpl implements JsonDataRepository {
    @Override
    public List<CoinDeskBlogModel> getAllBlogData() {
        // Triển khai lấy dữ liệu từ nguồn nào đó và trả về danh sách blog
        return Collections.emptyList();
    }
}
