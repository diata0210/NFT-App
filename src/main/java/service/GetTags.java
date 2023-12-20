package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.BlogModel;
public class GetTags {


    public static List<String> getAllTags(int type, String title,String date) {
        switch (type) {
            case 0:
                return allTags();
            case 1:
                return getAllTagsFilteredByName(title);
            case 2:
                return getTagsByDay(date);
            case 3:
                return getTagsByMonth(date);
            default:
                return new ArrayList<>();
        }
    }

    private static List<String> allTags() {
        List<String> allTags = new ArrayList<>();
        List<BlogModel> allArticles = GetArticles.allArticles();
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
        List<BlogModel> allArticles = GetArticles.allArticles();
        for (BlogModel article : allArticles) {
            for (String tag : article.getRelatedTags()) {
                if (tag.contains(name) && !allTags.contains(tag)) {
                    allTags.add(tag);
                }
            }
        }
        return allTags;
    }

    private static List<String> getTagsByDay(String date) {
        String day = date.substring(0, 2);
        Map<String, Integer> tagFrequency = GetTagFrequencyByDay.getTagFrequencyByDay(day);
        return new ArrayList<>(tagFrequency.keySet());
    }

    private static List<String> getTagsByMonth(String date) {
        String month = date.substring(3, 5);
        if (month.length() == 2) {
            Map<String, Integer> tagFrequency = GetTagFrequencyByMonth.getTagFrequencyByMonth(month);
            return new ArrayList<>(tagFrequency.keySet());
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> allTags = getAllTags(0, "","");
        List<String> tagsByName = getAllTags(1, "NFT","");
        List<String> tagsByDay = getAllTags(2, "","08-01-2023");
        List<String> tagsByMonth = getAllTags(3, "","01-08-2023");

        System.out.println("All Tags:");
        allTags.forEach(System.out::println);

        System.out.println("\nTags by Name:");
        tagsByName.forEach(System.out::println);

        System.out.println("\nTags by Day:");
        tagsByDay.forEach(System.out::println);

        System.out.println("\nTags by Month:");
        tagsByMonth.forEach(System.out::println);
    }
}

