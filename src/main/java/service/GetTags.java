package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.BlogModel;

public class GetTags {

  public static List<String> getAllTags(int type, String title, String date) {
    switch (type) {
      case 0:
        return getAllTagsFilteredByName(title);
      case 1:
        return getTagsByDay(date);
      case 2:
        return searchTagsByDateAndName(title, date);
      case 3:
        return searchTagsByMonthAndName(title, date);
      default:
        return new ArrayList<>();
    }
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
    Map<String, Integer> tagFrequency = GetTagFrequencyByDays.getTagFrequencyByDay(date);
    return new ArrayList<>(tagFrequency.keySet());
  }

  public static List<String> searchTagsByDateAndName(String title, String date) {
    List<BlogModel> allArticles = GetArticles.allArticles();
    Map<String, Integer> tagFrequencyByMonth = GetTagFrequencyByDays.getTagFrequencyByDay(date);
    Set<String> tags = new HashSet<>();
    for (String key : tagFrequencyByMonth.keySet()) {
      for (BlogModel article : allArticles) {
        if (matchesDate(article.getDate(), date) && matchesName(article.getTitle(), title)) {
          for (String tag : article.getRelatedTags()) {
            if (!tags.contains(tag)) {
              if (key.equals(tag)) {
                tags.add(tag);
              }
            }
          }
        }
      }
    }
    return new ArrayList<>(tags);
  }

  private static boolean matchesDate(String articleDate, String searchDate) {
    if (articleDate != null && articleDate.length() >= 10 && searchDate != null && searchDate.length() >= 10) {
      return articleDate.substring(0, 10).equals(searchDate.substring(0, 10));
    }
    return false;
  }

  public static List<String> searchTagsByMonthAndName(String title, String monthYear) {
    List<BlogModel> allArticles = GetArticles.allArticles();
    Map<String, Integer> tagFrequencyByMonth = GetTagFrequencyByMonths.getTagFrequencyByMonth(monthYear);
    Set<String> tags = new HashSet<>();
    for (String key : tagFrequencyByMonth.keySet()) {
      for (BlogModel article : allArticles) {
        if (matchesMonthYear(article.getDate(), monthYear) && matchesName(article.getTitle(), title)) {
          for (String tag : article.getRelatedTags()) {
            if (!tags.contains(tag)) {
              if (key.equals(tag)) {
                tags.add(tag);
              }
            }
          }
        }
      }
    }
    return new ArrayList<>(tags);
  }

  private static boolean matchesMonthYear(String articleDate, String searchMonthYear) {
    if (articleDate != null && articleDate.length() >= 7 && searchMonthYear != null
        && searchMonthYear.length() >= 7) {
      return articleDate.substring(0, 7).equals(searchMonthYear.substring(0, 7));
    }
    return false;
  }

  private static boolean matchesName(String articleTitle, String searchName) {
    return articleTitle != null && articleTitle.toLowerCase().contains(searchName.toLowerCase());
  }

  public static void main(String[] args) {

    List<String> tagsByName = getAllTags(0, "NFT", "");
    List<String> tagsByMonthAndName = getAllTags(3, "NFT", "2023-08-01");
    List<String> tagsByDateAndName = getAllTags(2, "NFT", "2023-08-01");
    System.out.println("\nTags by Name:");
    tagsByName.forEach(System.out::println);

    System.out.println("\nTags by Day:");
    tagsByDateAndName.forEach(System.out::println);

    System.out.println("\nTags by Month:");
    tagsByMonthAndName.forEach(System.out::println);
  }
}