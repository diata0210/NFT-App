package com.example.service;

import com.example.converter.JsonToArticleConverter;
import com.example.model.Article;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ArticleService {

    private final JsonToArticleConverter jsonToArticleConverter;

    public ArticleService(JsonToArticleConverter jsonToArticleConverter) {
        this.jsonToArticleConverter = jsonToArticleConverter;
    }

    public List<Article> getArticlesFromJson(String json) throws IOException {
        return jsonToArticleConverter.convertToList(json);
    }
}
