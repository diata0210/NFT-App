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

import com.fasterxml.jackson.databind.ObjectMapper;

import models.UpyoBlogModel;

public class UpyoBlogCrawl implements BaseCrawler {
    private final String URL = "https://upyo.com/en/blog/nft/";
    private final int PAGES = 8;

    @Override
    public List<String> getUrls() {
        List<String> listUrls = new ArrayList<String>();
        try {
            for(int i = 1; i <= PAGES; i++){
                Document doc = Jsoup.connect(URL + "page/" + Integer.toString(i))
                                    .userAgent("Jsoup Client").timeout(20000).get();
                Elements divs = doc.select("div.col-md-6.col-xl-4.topics_topicOuter__fln0m:not(.topics_NftBanner__rZgeo)");

                for(Element div : divs){
                    Element firstATag = div.select("a").first();
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
        try (Writer writer = new FileWriter("project/src/main/java/data/UpyoBlog.json")) {
            writer.write('[');
            int count = 0;
            for(String url : listUrls){
                try {
                    count++;
                    Document doc = Jsoup.connect(url).userAgent("Jsoup Client").timeout(30000).get();
                    String title = doc.select("h1.article_articleTitle__4Ft4e").text();

                    Element authorElement = doc.select("div.article_articleProps__JsEXX.article_light__UWgo_ ").first();
                    Element authorElement2 = authorElement.select("a").first();
                    String author = authorElement2.text();

                    String date = doc.select("span:contains(2023)").first().text(); 


                    Elements content = doc.select("h2");
                    List<String> listContent = new ArrayList<String>();

                    for(Element list : content){
                        String listString = list.text();
                        listContent.add(listString);
                    }

                    UpyoBlogModel model = new UpyoBlogModel(title, author, date, url, listContent);
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
        UpyoBlogCrawl crawler = new UpyoBlogCrawl();
        crawler.crawlData();
    }
}
