package service;

import java.util.ArrayList;
import java.util.List;
import models.BlogModel;

public class getAllTags {

    public static List<String> allTags() {
        List<String> allTags = new ArrayList<>();
        List<BlogModel> allArticles = getAllArticles.allArticles();
        for (BlogModel article : allArticles) {
            for (String tags : article.getRelatedTags()) {
                if (!allTags.contains(tags)) {
                    allTags.add(tags);
                }
            }
        }
        return allTags;
    }
    public static void main(String[] args) {
        List<String> allTags=allTags();
        for (String tags:allTags){
            System.out.println(tags);
        }
}
}