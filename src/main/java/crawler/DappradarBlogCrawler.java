package crawler;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DappradarBlogCrawler implements BaseCrawler {
    private final String URL = "https://playtoearn.net";
    private final String SEARCH_URL = "/news?page=";
    // private final int 
    public List<String> getUrls() {
        List<String> listUrls = new ArrayList<String>();
        System.setProperty("webdriver.chrome.driver",
                "D:\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);
        // for(int i = 1; i < )
        driver.get(URL + SEARCH_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement h1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("newsbox-content")));
        driver.quit();
        return listUrls;
    }

    @Override
    public void crawlData() {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {

    }
}
