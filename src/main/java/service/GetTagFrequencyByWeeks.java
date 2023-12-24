package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import models.BlogModel;

public class GetTagFrequencyByWeeks {

  public static Map<String, Integer> getTagFrequencyByWeek(String dateString) {
    LocalDate date = parseDate(dateString);
    if (date == null) {
      System.out.println("Invalid date format.");
      return new HashMap<>();
    }
    WeekFields weekFields = WeekFields.of(Locale.getDefault());
    int targetWeek = date.get(weekFields.weekOfWeekBasedYear());
    int targetYear = date.getYear();

    List<BlogModel> allArticles = GetArticles.allArticles();
    Map<String, Integer> tagFrequency = new HashMap<>();

    for (BlogModel article : allArticles) {
      LocalDate articleDate = tryParseDate(article.getDate());
      if (articleDate != null && articleDate.getYear() == targetYear
          && articleDate.get(weekFields.weekOfWeekBasedYear()) == targetWeek) {
        for (String tag : article.getRelatedTags()) {
          tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
        }
      }
    }

    return sortMapByValue(tagFrequency);
  }

  private static LocalDate parseDate(String dateString) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDate.parse(dateString, formatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static LocalDate tryParseDate(String dateString) {
    return parseDate(dateString);
  }

  public static String getWeekOfYear(String dateString) {
    LocalDate date = tryParseDate(dateString);
    if (date == null) {
      return null;
    }
    WeekFields weekFields = WeekFields.of(Locale.getDefault());
    int week = date.get(weekFields.weekOfWeekBasedYear());
    int year = date.getYear();
    return String.format("%d-W%d", year, week);
  }

  private static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
    return map.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new));
  }
}