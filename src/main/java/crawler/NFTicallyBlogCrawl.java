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

import models.BlogNFTicallyModel;


public class NFTicallyBlogCrawl implements BaseCrawler {

    private final String URL = "https://www.nftically.com/blog/tag/nfts/";
    private final int PAGE = 10;

    @Override
    public List<String> getUrls() {
        List<String> listUrls = new ArrayList<String>();
            for(int i = 1; i <= PAGE; i++){
                String url = URL + "page/" + Integer.toString(i) + "/";
                listUrls.add(url);
            }
        return listUrls;
    }

    @Override
    public void crawlData() {
        List<String> listUrls = getUrls();
        try (Writer writer = new FileWriter("project/src/main/java/data/BlogNFTically.json")){
            writer.write('[');
            int count = 0;
            for(String url : listUrls){
                Document document = Jsoup.connect(url).userAgent("Jsoup Client").timeout(30000).get();
                Elements divs = document.select("div.blog-item-wrap");
                for(Element div : divs){
                    String title = div.select("h4.blog-title").text();
                    String author = div.select("div.author").text();
                    String date = div.select("div.blog-date").text();
                    String tag = div.select("span.saspot-label").text();

                    BlogNFTicallyModel model = new BlogNFTicallyModel(title, author, date, tag);
                    ObjectMapper mapper = new ObjectMapper();
                    writer.write(mapper.writeValueAsString(model));
                    if (count < listUrls.size()){
                        writer.write(",");
                    }
                    writer.write("\n");
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
