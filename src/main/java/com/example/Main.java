package com.example;

import com.example.converter.JsonToArticleConverter;
import com.example.service.ArticleService;
import com.example.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create an instance of ObjectMapper to be used throughout
        ObjectMapper objectMapper = new ObjectMapper();

        // Create an instance of JsonToArticleConverter and pass the ObjectMapper
        JsonToArticleConverter converter = new JsonToArticleConverter(objectMapper);

        // Create an instance of ArticleService and pass the JsonToArticleConverter
        ArticleService articleService = new ArticleService(converter);

        String pathToJsonFile = "src/main/resources/article.json";
        String pathToOutputFile = "src/main/resources/articleOutput.json";

        try {
            String jsonInput = new String(Files.readAllBytes(Paths.get(pathToJsonFile)), StandardCharsets.UTF_8);

            // Assuming that the service method now returns a list of articles
            List<Article> articles = articleService.getArticlesFromJson(jsonInput);

            // Iterate over articles and convert them to JSON, then append to the file
            for (Article article : articles) {
                String jsonOutput = objectMapper.writeValueAsString(article);
                Files.write(Paths.get(pathToOutputFile), jsonOutput.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

            System.out.println("Article objects have been written to the file: " + pathToOutputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
