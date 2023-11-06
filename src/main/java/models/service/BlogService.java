package models.service;

import models.CoinDeskBlogModel;

import java.util.List;

public interface BlogService {
    List<CoinDeskBlogModel> getAllBlogData();
}
