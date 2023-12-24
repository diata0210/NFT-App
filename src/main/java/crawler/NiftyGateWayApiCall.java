package crawler;

import java.io.FileWriter;
import java.io.IOException;
import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NiftyGateWayApiCall implements BaseCrawler {

  public void crawlData(){
    try {
      OkHttpClient client = new OkHttpClient().newBuilder()
          .build();
      MediaType mediaType = MediaType.parse("text/plain");
      Request request = new Request.Builder()
          .url(
              "https://api.niftygateway.com/marketplace/nifty-types/?page=%7B%22current%22:1,%22size%22:52%7D&filter=%7B%22available%22:%22true%22,%22baseChain%22:\\[\\],%22listing_type%22:\\[\\],%22volume_sales_in_last_24_hours%22:1,%22category_name__in%22:\\[\\],%22tag_name__in%22:\\[\\]%7D&sort=%7B%7D")
          .addHeader("authority", "api.niftygateway.com")
          .addHeader("accept", "application/json, text/plain, */*")
          .addHeader("accept-language", "vi,en;q=0.9,en-GB;q=0.8,en-US;q=0.7")
          .addHeader("cookie",
              "_ga=GA1.1.1630557261.1697970298; _gcl_au=1.1.1915943915.1697970300; _scid=a7000c1d-c1d6-48e7-b482-8f4d5cde61ae; __zlcmid=1ISmDN3lLX1wAyg; _sctr=1%7C1699030800000; csrftoken=oxK6g8bvoZVBPJfTJc6WsfbyXaWklTT0iZNN9Wvn7TiQ84OLHZtsSPQwcrGY8Nnm; _scid_r=a7000c1d-c1d6-48e7-b482-8f4d5cde61ae; _ga_XN0S484YR4=GS1.1.1699325124.7.1.1699325142.42.0.0; nifty_write_lock=y; AWSALB=cQGC2uUEYMTH5Uku+/q35pbWi7LdA3wrFvbOzkBDPPf/UVS2QsLiO7rLGIksC8+nDaFnRo+rIldfv7/HHUWHp1i5bBnmanVqIDFqauJVAj4NQIKV/HvXLhOxAVtm; AWSALBCORS=cQGC2uUEYMTH5Uku+/q35pbWi7LdA3wrFvbOzkBDPPf/UVS2QsLiO7rLGIksC8+nDaFnRo+rIldfv7/HHUWHp1i5bBnmanVqIDFqauJVAj4NQIKV/HvXLhOxAVtm; mp_1cd1557698e78afc5a6139251ac3996c_mixpanel=%7B%22distinct_id%22%3A%20%2218b56eaa01b46b-0a6a4ca9fc5596-26031151-e1000-18b56eaa01cc1b%22%2C%22%24device_id%22%3A%20%2218b56eaa01b46b-0a6a4ca9fc5596-26031151-e1000-18b56eaa01cc1b%22%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fwww.google.com%2F%22%2C%22%24initial_referring_domain%22%3A%20%22www.google.com%22%2C%22__mps%22%3A%20%7B%22%24os%22%3A%20%22Windows%22%2C%22%24browser%22%3A%20%22Chrome%22%2C%22%24browser_version%22%3A%20119%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fwww.google.com%2F%22%2C%22%24initial_referring_domain%22%3A%20%22www.google.com%22%7D%2C%22__mpso%22%3A%20%7B%7D%2C%22__mpus%22%3A%20%7B%7D%2C%22__mpa%22%3A%20%7B%7D%2C%22__mpu%22%3A%20%7B%7D%2C%22__mpr%22%3A%20%5B%5D%2C%22__mpap%22%3A%20%5B%5D%2C%22%24search_engine%22%3A%20%22google%22%2C%22__timers%22%3A%20%7B%22Nifty%20Type%20Viewed%22%3A%201699107669456%2C%22Marketplace%20Results%20Page%20Viewed%22%3A%201699325145424%7D%7D; AWSALB=i4VJrBCVm6hTHIKVsRsG+NR7+l50hiRc9pCFvYCY5MtMb6QUtr6rvBkcQNSabB6HU13zRom7dU0yh+CZj6GZXCJW7i5viiGvDJSryZlw2WqUwtlKgIyO53+CUphS; AWSALBCORS=i4VJrBCVm6hTHIKVsRsG+NR7+l50hiRc9pCFvYCY5MtMb6QUtr6rvBkcQNSabB6HU13zRom7dU0yh+CZj6GZXCJW7i5viiGvDJSryZlw2WqUwtlKgIyO53+CUphS; nifty_write_lock=y")
          .addHeader("origin", "https://www.niftygateway.com")
          .addHeader("referer", "https://www.niftygateway.com/")
          .addHeader("sec-ch-ua",
              "\"Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"")
          .addHeader("sec-ch-ua-mobile", "?0")
          .addHeader("sec-ch-ua-platform", "\"Windows\"")
          .addHeader("sec-fetch-dest", "empty")
          .addHeader("sec-fetch-mode", "cors")
          .addHeader("sec-fetch-site", "same-site")
          .addHeader("user-agent",
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
          .addHeader("x-sid", "e454e4e2-0080-4a18-bb62-f7c251b7cd2b")
          .addHeader("x-time", "1699325145479")
          .build();

      try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
          String responseData = response.body().string();
          System.out.println(responseData);
          try {
            FileWriter file = new FileWriter(JsonURL.NIFTYGATEWAY);
            file.write(responseData);
            file.flush();
            file.close();
            System.out.println("JSON data has been successfully saved to the file.");
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          System.out.println("Request failed with code: " + response.code());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    NiftyGateWayApiCall model = new NiftyGateWayApiCall();
    model.fetchData();
  }
}