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

import models.TheartNewPaperBlogModel;

public class TheartNewsPaperCrawler implements BaseCrawler {

  private final String BASE_URL = "https://www.theartnewspaper.com";
  private final String SEARCH_URL = "/keywords/nft?page=";
  private final int NUM_OF_PAGE = 8;

  public List<String> getUrls() {
    List<String> listUrls = new ArrayList<String>();
    try {
      for (int i = 1; i <= NUM_OF_PAGE; i++) {
        Document doc = Jsoup.connect(BASE_URL + SEARCH_URL + Integer.toString(i)).userAgent("Jsoup client")
            .timeout(20000).get();
        Elements aTags = doc.select("a.block.text-gray-800.transition-colors.duration-default.cursor-pointer.mb-md");
        for (Element aTag : aTags) {
          String url = aTag.attr("href");
          listUrls.add(url);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listUrls;
  }

  public void crawlData() {
    List<String> listUrls = getUrls();
    try (Writer writer = new FileWriter(JsonURL.ARTNEWSPAPER)) {
      writer.write('[');
      int count = 0;
      for (String url : listUrls) {
        try {
          count++;
          Document doc = Jsoup.connect(BASE_URL + url).userAgent("Jsoup client").timeout(30000).get();
          String title = doc.title();
          Element descElement = doc
              .select("h2.font-sans-regular.text-lg.leading-tighter.tracking-wider.max-w-col-3.print-max-w-full")
              .first();
          String desc = (descElement != null) ? descElement.text() : "N/A";
          Element authorElement = doc.select("a.font-sans-bold.shadow-link.transition-all.duration-default").first();
          String author = (authorElement != null) ? authorElement.text() : "N/A";
          Element dateElement = doc.select("meta[property=article:published_time]").first();
          String date = dateElement.attr("content");
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]X");
          LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          date = dateTime.format(outputFormatter);
          Elements tags = doc.select(
              "a.font-sans-regular.text-base.leading-none.tracking-wide.text-red-900.px-md.py-xs.mb-base.whitespace-no-wrap.bg-gray-50.transition-colors.duration-default");
          List<String> relatedTags = new ArrayList<String>();
          for (Element tag : tags) {
            String tagText = tag.text();
            relatedTags.add(tagText);
          }
          TheartNewPaperBlogModel model = new TheartNewPaperBlogModel(title, desc, author, date, relatedTags);
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
    TheartNewsPaperCrawler crawler = new TheartNewsPaperCrawler();
    crawler.crawlData();
  }
}
