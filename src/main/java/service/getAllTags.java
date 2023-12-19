package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.BlogModel;

public class getAllTags {
    public static List<String> getAllTags(int type, String parameter) {
        switch (type) {
            case 0:
                return allTags();
            case 1:
                return getAllTagsFilteredByName(parameter);
            case 2:
                return getTagsByDay(parameter);
            case 3:
                return getTagsByMonth(parameter);
            default:
                return new ArrayList<>();
        }
    }

    private static List<String> allTags() {
        List<String> allTags = new ArrayList<>();
        List<BlogModel> allArticles = getAllArticles.allArticles();
        for (BlogModel article : allArticles) {
            for (String tag : article.getRelatedTags()) {
                if (!allTags.contains(tag)) {
                    allTags.add(tag);
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
                if (tag.contains(name) && !allTags.contains(tag)) {
                    allTags.add(tag);
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
        if (month.length() == 2) {
            Map<String, Integer> tagFrequency = getTagFrequencyByMonth.getTagFrequencyByMonth(month);
            return new ArrayList<>(tagFrequency.keySet());
        } else {
            return null;
        }
    }
}
