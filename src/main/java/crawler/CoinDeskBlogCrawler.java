package crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.remote.tracing.Tags;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.CoinDeskBlogModel;

public class CoinDeskBlogCrawler implements BaseCrawler {
    private final String SEARCH_URL = "https://www.coindesk.com";
    private final String BASE_URL = "nfts";
    private final int NUM_OF_PAGE = 9;

    @Override
    public List<String> getUrls() {
        List<String> listUrls = new ArrayList<String>();
        try {
            for (int i = 1; i <= NUM_OF_PAGE; i++) {
                Document document = Jsoup.connect(SEARCH_URL + "/tag/" + BASE_URL + "/" + Integer.toString(i) + "/")
                        .userAgent("Jsoup client").timeout(20000).get();
                Elements divs = document.select("h6.typography__StyledTypography-sc-owin6q-0.diMXjy");
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
        try (Writer writer = new FileWriter("src/main/java/data/CoinDeskBlog.json")) {
            writer.write('[');
            int count = 0;
            for (String url : listUrls) {
                try {
                    count += 1;
                    Document doc = Jsoup.connect(SEARCH_URL + url).userAgent("Jsoup client").timeout(20000).get();
                    String title = doc.select("h1.typography.StyledTypography-sc-owin6q-0.bSOJsQ").text();
                    String desc = doc.select("h2.typography__StyledTypography-sc-owin6q-0.irVmAp").text();
                    String author = doc.select("a.typography__StyledTypography-sc-owin6q-0.fqtDyG").text();
                    String date = doc.select("span.typography__StyledTypography-sc-owin6q-0.hcIsFR").text();
                    Elements tags = doc.select("a.article-tagsstyles__TagPill-sc-17t0gri-0.eJTFpe.light");
                    List<String> relatedTags = new ArrayList<String>();

                    for (Element tag : tags) {
                        String tagText = tag.text();
                        relatedTags.add(tagText);
                    }
                    CoinDeskBlogModel model = new CoinDeskBlogModel(title, desc, author, date, relatedTags);
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
        CoinDeskBlogCrawler crawler = new CoinDeskBlogCrawler();
        crawler.crawlData();
    }
}
