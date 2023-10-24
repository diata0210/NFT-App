package crawler;

import java.util.List;

public interface BaseCrawler {
    List<String> getUrls();
    void crawlData();
}
