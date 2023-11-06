package models.repository;

import models.CoinDeskBlogModel;

import java.util.List;

public interface JsonDataRepository {
    List<CoinDeskBlogModel> getAllBlogData();
}
