package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.BlogModel;

public class GetTags {

  public static List<String> getAllTags(int type, String title, String date) {
      if(date!=null){
        if (date != null && date.length() >= 10) {
          String year = date.substring(0, 4);
          String month = date.substring(5, 7);
          String day = date.substring(8, 10);
          date= year + "-" + day + "-" + month;
      }
      }
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

    // Lọc các bài viết phù hợp với tiêu đề và ngày
    List<String> filteredTags = new ArrayList<>();
    for (BlogModel article : allArticles) {
      if (matchesDate(article.getDate(), date) && matchesName(article.getTitle(), title)) {
        filteredTags.addAll(article.getRelatedTags());
      }
    }

    // Sắp xếp các tag theo tần suất xuất hiện
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

    // Lọc các bài viết phù hợp với tiêu đề và tháng
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

    // Sắp xếp các tag theo tần suất xuất hiện
    return filteredTags.stream()
        .filter(tag -> tagFrequencyByWeek.containsKey(tag))
        .sorted((tag1, tag2) -> tagFrequencyByWeek.get(tag2).compareTo(tagFrequencyByWeek.get(tag1)))
        .distinct()
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {

    List<String> tagsByName = getAllTags(0, "NFT", "");
    List<String> tagsByMonthAndName = getAllTags(2, "NFT", "2023-01-08");
    List<String> tagsByDateAndName = getAllTags(1, "NFT", "2023-01-08");
    List<String> tagsByWeekAndName = getAllTags(3, "NFT", "2023-01-08");
    System.out.println("\nTags by Name:");
    tagsByName.forEach(System.out::println);

    System.out.println("\nTags by Day:");
    tagsByDateAndName.forEach(System.out::println);
    System.out.println("\nTags by Week:");
    tagsByWeekAndName.forEach(System.out::println);
    System.out.println("\nTags by Month:");
    tagsByMonthAndName.forEach(System.out::println);
  }
}