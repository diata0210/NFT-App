package com.example.converter;

import com.example.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonToArticleConverter {

    private final ObjectMapper objectMapper;

    public JsonToArticleConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        // Correct the date format to handle 'p.m.' and 'a.m.'
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy 'at' h:mm a 'UTC'", Locale.ENGLISH);
        dateFormat.setLenient(false);

        // Custom deserializer to handle dates in the specific format with 'p.m.' and
        // 'a.m.'
        SimpleModule dateModule = new SimpleModule();
        dateModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                String dateAsString = jsonParser.getText().replace("p.m.", "PM").replace("a.m.", "AM");
                try {
                    return dateFormat.parse(dateAsString);
                } catch (ParseException e) {
                    throw new IOException("Failed to parse the date: " + dateAsString, e);
                }
            }
        });

        // Register the custom module with the provided ObjectMapper
        this.objectMapper.registerModule(dateModule);
    }

    public List<Article> convertToList(String jsonArticles) throws IOException {
        JsonNode arrayNode = objectMapper.readTree(jsonArticles);
        List<Article> articles = new ArrayList<>();

        if (arrayNode.isArray()) {
            for (JsonNode node : arrayNode) {
                Article article = objectMapper.treeToValue(node, Article.class);
                articles.add(article);
            }
        }
        return articles;
    }

    // ... Additional methods if needed
}
