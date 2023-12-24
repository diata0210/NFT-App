package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.BlogModel;

public class GetTags {

  public static List<String> getAllTags(int type, String title, String date) {
    switch (type) {
      case 0:
        return getAllTagsFilteredByName(title);
      case 1:
        return searchTagsByDateAndName(title, date);
      case 2:
        return searchTagsByMonthAndName(title, date);
      case 3:
        return searchTagsByWeekAndName(title, date);
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
    Map<String, Integer> tagFrequencyByDay = GetTagFrequencyByDays.getTagFrequencyByDay(date);
    List<String> filteredTags = new ArrayList<>();
    for (BlogModel article : allArticles) {
      if (matchesDate(article.getDate(), date) && matchesName(article.getTitle(), title)) {
        filteredTags.addAll(article.getRelatedTags());
      }
    }
    return filteredTags.stream()
        .filter(tagFrequencyByDay::containsKey)
        .sorted((tag1, tag2) -> tagFrequencyByDay.get(tag2).compareTo(tagFrequencyByDay.get(tag1)))
        .distinct()
        .collect(Collectors.toList());
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
    List<String> filteredTags = new ArrayList<>();
    for (BlogModel article : allArticles) {
      if (matchesMonthYear(article.getDate(), monthYear) && matchesName(article.getTitle(), title)) {
        filteredTags.addAll(article.getRelatedTags());
      }
    }

    return filteredTags.stream()
        .filter(tagFrequencyByMonth::containsKey)
        .sorted((tag1, tag2) -> tagFrequencyByMonth.get(tag2).compareTo(tagFrequencyByMonth.get(tag1)))
        .distinct()
        .collect(Collectors.toList());
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

  public static List<String> searchTagsByWeekAndName(String title, String dateString) {
    List<BlogModel> allArticles = GetArticles.allArticles();
    Map<String, Integer> tagFrequencyByWeek = GetTagFrequencyByWeeks.getTagFrequencyByWeek(dateString);

    List<String> filteredTags = new ArrayList<>();
    for (BlogModel article : allArticles) {
      String articleWeekYear = GetTagFrequencyByWeeks.getWeekOfYear(article.getDate());
      if (articleWeekYear != null && articleWeekYear.equals(GetTagFrequencyByWeeks.getWeekOfYear(dateString))
          && matchesName(article.getTitle(), title)) {
        filteredTags.addAll(article.getRelatedTags());
      }
    }
    return filteredTags.stream()
        .filter(tag -> tagFrequencyByWeek.containsKey(tag))
        .sorted((tag1, tag2) -> tagFrequencyByWeek.get(tag2).compareTo(tagFrequencyByWeek.get(tag1)))
        .distinct()
        .collect(Collectors.toList());
  }
}