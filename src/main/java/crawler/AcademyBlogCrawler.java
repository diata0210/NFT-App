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

// import models.AcademyBlogModel;

public class AcademyBlogCrawler implements BaseCrawler {
    private final String BASE_URL = "https://academy.moralis.io/blog/";
    private final String SEARCH_URL = "nfts";
    private final int NUM_OF_PAGE = 8;

    public List<String> getUrls() {
        List<String> listUrls = new ArrayList<String>();
        try {
            for (int page = 1; page <= NUM_OF_PAGE; page++) {
                Document doc = Jsoup.connect(BASE_URL + SEARCH_URL + "/page/" + Integer.toString(page)).get();
                Elements divs = doc.select("div.elementor-post__text");
                for (Element aTag : divs) {
                    Element firstATag = aTag.select("a").first();
                    if (firstATag != null) {
                        String tempUrl = firstATag.attr("href");
                        listUrls.add(tempUrl);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUrls;
    }

    public void crawlData() {
        // List<String> listUrls = getUrls();
        // int count = 0;
        // try (Writer writer = new FileWriter("src/main/java/data/Academy.json")) {
        //     writer.write('[');
        //     for (String url : listUrls) {
        //         try {
        //             count++;
        //             Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(20000).get();
        //             String title = doc.title();

        //             Element authorDiv = doc.select(
        //                     "div.elementor-element.elementor-element-b3d7692.elementor-widget.elementor-widget-heading")
        //                     .first();
        //             String author = "undefined";
        //             if (authorDiv != null) {
        //                 author = authorDiv.select("div.elementor-heading-title.elementor-size-default").text();
        //             }
        //             Element dateDiv = doc
        //                     .select("elementor-element.elementor-element-773cabf.elementor-widget.elementor-widget-heading")
        //                     .first();
        //             String date = "undefined";
        //             if (dateDiv != null) {
        //                 date = dateDiv.select("div.elementor-heading-title.elementor-size-default").text();
        //             }
        //             List<String> listAuthor = new ArrayList<String>();
        //             listAuthor.add(author);
        //             List<String> listTitle = new ArrayList<String>();
        //             listTitle.add(title);
        //             List<String> listDate = new ArrayList<String>();
        //             listDate.add(date);
        //             AcademyBlogModel tempAcaModel = new AcademyBlogModel(author, title, date, url, listAuthor,
        //                     listTitle, listDate);
        //             ObjectMapper mapper = new ObjectMapper();
        //             System.out.println(mapper.writeValueAsString(tempAcaModel));
        //             writer.write(mapper.writeValueAsString(tempAcaModel));
        //             if (count < listUrls.size())
        //                 writer.write(",");
        //             writer.write("\n");
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        //     writer.write(']');
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    public static void main(String[] args) {
        AcademyBlogCrawler crawl = new AcademyBlogCrawler();
        crawl.crawlData();
    }
}
