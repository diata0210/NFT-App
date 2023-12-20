package service;

import java.util.ArrayList;
import java.util.List;
import models.BlogModel;

public class TagsSearchService {

    public static List<String> searchTagsContainingString(String searchString) {
        List<String> matchingTags = new ArrayList<>();
        List<BlogModel> allArticles = getAllArticles.allArticles();
        for (BlogModel article : allArticles) {
            for (String tag : article.getRelatedTags()) {
                if (tag.contains(searchString) && !matchingTags.contains(tag)) {
                    matchingTags.add(tag);
                }
            }
        }
        return matchingTags;
    }

    public static void main(String[] args) {
        String searchString = "NF"; // Thay thế bằng chuỗi bạn muốn tìm
        List<String> foundTags = searchTagsContainingString(searchString);
        if (foundTags.isEmpty()) {
            System.out.println("No tags found containing: " + searchString);
        } else {
            System.out.println("Tags containing '" + searchString + "':");
            for (String tag : foundTags) {
                System.out.println(tag);
            }
        }
    }
}
