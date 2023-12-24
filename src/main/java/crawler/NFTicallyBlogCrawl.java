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

import models.BlogNFTicallyModel;

public class NFTicallyBlogCrawl implements BaseCrawler {

  private final String URL = "https://www.nftically.com/blog/tag/nfts/";
  private final int NUM_OF_PAGE = 10;

  public List<String> getUrls() {
    List<String> listUrls = new ArrayList<String>();
    try {
      for (int i = 1; i <= NUM_OF_PAGE; i++) {
        Document document = Jsoup.connect(URL + "page/" + Integer.toString(i) + "/")
            .userAgent("Jsoup client").timeout(20000).get();
        Elements divs = document.select("div.blog-item-wrap");
        for (Element aTag : divs) {
          Element firstATag = aTag.select("a").first();
          if (firstATag != null) {
            String tmpUrl = firstATag.attr("href");
            listUrls.add(tmpUrl);
          }
        }
      }
    } catch (IOException err) {
      err.printStackTrace();
    }
    return listUrls;
  }

  @Override
  public void crawlData() {
    List<String> listUrls = getUrls();
    try (Writer writer = new FileWriter(JsonURL.NFTICALLY)) {
      writer.write('[');
      int count = 0;
      for (String url : listUrls) {
        try {
          Document document = Jsoup.connect(url).userAgent("Jsoup Client").timeout(60000).get();
          String title = document.title();

          Element descElement = document.select("meta[name=description]").first();
          String desc = (descElement != null) ? descElement.attr("content") : "";

          Element metaTag = document.select("meta[name=author]").first();
          String author = (metaTag != null) ? metaTag.attr("content") : "";

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

          Element tagElements = document.select("div.blog-tags").first();
          List<String> relatedTags = new ArrayList<>();
          if (tagElements != null) {
            Elements tags = tagElements.select("span.saspot-label");
            for (Element tag : tags) {
              String tagText = tag.text();
              relatedTags.add(tagText);
            }
          }
          BlogNFTicallyModel model = new BlogNFTicallyModel(title, desc, author, date, relatedTags);
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
    NFTicallyBlogCrawl crawler = new NFTicallyBlogCrawl();
    crawler.crawlData();
  }
}
