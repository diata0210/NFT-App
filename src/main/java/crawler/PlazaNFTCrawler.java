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
import models.PlazaNFTModel;

public class PlazaNFTCrawler implements BaseCrawler {
  private final String SEARCH_URL = "https://nftplazas.com";
  private final String BASE_URL = "/all-posts/";
  private final int NUM_OF_PAGE = 53;

  public List<String> getUrls() {
    List<String> listUrls = new ArrayList<String>();
    try {
      for (int i = 1; i <= NUM_OF_PAGE; i++) {
        Document doc = Jsoup.connect(SEARCH_URL + BASE_URL + "page/" + i + "/")
            .userAgent("Jsoup Client").timeout(20000).get();
        Elements divs = doc.select("h2.post_title.entry-title");

        for (Element aTags : divs) {
          Element firstATag = aTags.select("a").first();
          if (firstATag != null) {
            String tmpUrl = firstATag.attr("href");
            listUrls.add(tmpUrl);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listUrls;
  }

  @Override
  public void crawlData() {
    List<String> listUrls = getUrls();
    try (Writer writer = new FileWriter(JsonURL.PLAZANFT)) {
      writer.write('[');
      int count = 0;
      for (String url : listUrls) {
        try {
          count++;
          Document doc = Jsoup.connect(url).userAgent("Jsoup Client").timeout(60000).get();

          Element titleElement = doc.select("title").first();
          String title = (titleElement != null) ? titleElement.text() : "";

          Element descElement = doc.select("meta[property=og:description]").first();
          String desc = (descElement != null) ? descElement.attr("content") : "";

          Element authorElement = doc.select("meta[name=twitter:data1]").first();
          String author = (authorElement != null) ? authorElement.attr("content") : "";

          Element dateElement = doc.select("meta[property=article:published_time]").first();
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

          String date = "";
          if (dateElement != null) {
            date = dateElement.attr("content");
            if (!date.isEmpty()) {
              LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
              date = dateTime.format(outputFormatter);
            }
          }

          Elements tags = doc.select("a[rel=tag]");
          List<String> relatedTags = new ArrayList<String>();

          for (Element tag : tags) {
            String tagText = tag.text();
            relatedTags.add(tagText);
          }
          PlazaNFTModel model = new PlazaNFTModel(title, desc, author, date, relatedTags);
          ObjectMapper mapper = new ObjectMapper();
          writer.write(mapper.writeValueAsString(model));
          if (count < listUrls.size())
            writer.write(",");
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
    PlazaNFTCrawler crawler = new PlazaNFTCrawler();
    crawler.crawlData();
  }
}
