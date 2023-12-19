package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.util.JsonURL;
import models.CtytoNewsBlogModel;

public class CryptoNewsBlogCrawler implements BaseCrawler {
  private final String BASE_URL = "https://cryptonews.com/news";
  private final String SEARCH_URL = "/nft-news/";
  private final int NUM_OF_PAGE = 34;

  public List<String> getUrls() {
    List<String> listUrls = new ArrayList<>();
    try {
      for (int i = 1; i <= NUM_OF_PAGE; i++) {
        Document doc = Jsoup.connect(BASE_URL + SEARCH_URL + "page/" + Integer.toString(i))
            .userAgent("Jsoup client").timeout(20000).get();
        Elements aTags = doc.select("a.article__image.article__image--sm-wider.mb-15");
        for (Element aTag : aTags) {
          String url = aTag.attr("href");
          listUrls.add(url);
        }
      }
    } catch (IOException er) {
      er.printStackTrace();
    }

    return listUrls;
  }

  @Override
  public void crawlData() {
    List<String> listUrls = getUrls();
    try (Writer writer = new FileWriter(JsonURL.CRYTONEWS)) {
      writer.write('[');
      int count = 0;
      for (String url : listUrls) {
        try {
          Document document = Jsoup.connect(url).userAgent("Jsoup Client").timeout(30000).get();
          String title = "";
          Element titleElement = document.select("div.article-single__content.category_contents_details h1.mb-40")
              .first();
          if (titleElement != null) {
            title = titleElement.text();
          }

          String author = "";
          Element authorElement = document.select("a.c-black").first();
          if (authorElement != null) {
            author = authorElement.text();
          }

          Element dateElement = document.select("meta[property=article:published_time]").first();
          String date = (dateElement != null) ? dateElement.attr("content") : "";
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
          LocalDateTime dateTime = null;
          if (date != null && !date.isEmpty()) {
            dateTime = LocalDateTime.parse(date, inputFormatter);
          }
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          if (dateTime != null) {
            date = dateTime.format(outputFormatter);
          }

          List<String> relatedTags = new ArrayList<>();
          Element tagElements = document.select("div.article-tag-box.text-lg-right").first();
          if (tagElements != null) {
            Elements tagElementsList = tagElements.select("a");
            for (Element tagElement : tagElementsList) {
              relatedTags.add(tagElement.text());
            }
          }
          CtytoNewsBlogModel model = new CtytoNewsBlogModel(title, author, date, relatedTags);
          ObjectMapper mapper = new ObjectMapper();
          writer.write(mapper.writeValueAsString(model));
          count++;
          if (count < listUrls.size()) {
            writer.write(",");
          }
          writer.write("\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      writer.write(']');
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    CryptoNewsBlogCrawler crawler = new CryptoNewsBlogCrawler();
    crawler.crawlData();
  }
}