package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.BlogModel;

public class getAllTags {
    // public static List<String> allTags() {
    // List<String> allTags = new ArrayList<>();
    // List<BlogModel> allArticles = getAllArticles.allArticles();
    // for (BlogModel article : allArticles) {
    // for (String tags : article.getRelatedTags()) {
    // if (!allTags.contains(tags)) {
    // allTags.add(tags);
    // }
    // }
    // }
    // return allTags;
    // }
    public static List<String> getAllTags(String name, String day, String month) {
        if (day != "" && name == "" && month == "") {
            return getTagsByDay(day);
        } else if (month != "" && day == "" && name == "") {
            return getTagsByMonth(month);
        } else if (name != "" && day == "" && month == "") {
            return getAllTagsFilteredByName(name);
        } else if (name == "" && day == "" && month == "") {
            return allTags();
        } else {
            return null;
        }
    }

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

    private static List<String> getAllTagsFilteredByName(String name) {
        List<String> allTags = new ArrayList<>();
        List<BlogModel> allArticles = getAllArticles.allArticles();

        for (BlogModel article : allArticles) {
            for (String tag : article.getRelatedTags()) {
                if (name == null || tag.contains(name)) {
                    if (!allTags.contains(tag)) {
                        allTags.add(tag);
                    }
                }
            }
        }
        return allTags;
    }

    private static List<String> getTagsByDay(String day) {
        Map<String, Integer> tagFrequency = getTagFrequencyByDay.getTagFrequencyByDay(day);
        return new ArrayList<>(tagFrequency.keySet());
    }

    private static List<String> getTagsByMonth(String month) {
        Map<String, Integer> tagFrequency = getTagFrequencyByMonth.getTagFrequencyByMonth(month);
        return new ArrayList<>(tagFrequency.keySet());
    }

    public static void main(String[] args) {
        // Example usage
        List<String> tags = getAllTags("NFT", "", ""); // Adjust the parameters as needed
        for (String tag : tags) {
            System.out.println(tag);
        }
        List<String> tagByDays = getAllTags("", "08-01", "");
        System.out.println("Hot tags by Day");
        for (String tag : tagByDays) {
            System.out.println(tag);
        }
        List<String> tagByMonth = getAllTags("", "", "08");
        System.out.println("Hot tags by Month");
        for (String tag : tagByMonth) {
            System.out.println(tag);
        }
        System.out.println("All Tag");
        List<String> allTags = getAllTags("", "", "");
        for (String tag : allTags) {
            System.out.println(tag);
        }
        System.out.println(getAllTags("NFT", null, "09"));
    }
}
