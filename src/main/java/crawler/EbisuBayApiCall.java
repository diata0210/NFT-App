package crawler;

import java.io.FileWriter;
import java.io.IOException;

import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EbisuBayApiCall implements BaseCrawler {

  @Override
  public void crawlData() {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
        .url("https://api.ebisusbay.com/collectioninfo?pageSize=50&direction=desc&sortBy=totalvolume7d&search=&page=1")
        .method("GET", null)
        .addHeader("authority", "api.ebisusbay.com")
        .addHeader("accept", "application/json, text/plain, */*")
        .addHeader("accept-language", "en-US,en;q=0.9")
        .addHeader("if-none-match", "\"15360-E/kvSMRkTgxHuNS8o2RR/tbKOq0\"")
        .addHeader("origin", "https://app.ebisusbay.com")
        .addHeader("referer", "https://app.ebisusbay.com/")
        .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Microsoft Edge\";v=\"120\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader("sec-ch-ua-platform", "\"Windows\"")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-site", "same-site")
        .addHeader("user-agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
        .addHeader("Cookie",
            "AWSALB=+xVis2hCwzjDQrv8Rx9TOhGA7uGFg1nrpQZUo3AxC2s8vadTaN0t9uTakyloNQ6gcBKzo5ImEmYXin7XlhUCv18oCA4PFT7alGotPoz7S54hJXqOohPn+KbIyVvj; AWSALBCORS=+xVis2hCwzjDQrv8Rx9TOhGA7uGFg1nrpQZUo3AxC2s8vadTaN0t9uTakyloNQ6gcBKzo5ImEmYXin7XlhUCv18oCA4PFT7alGotPoz7S54hJXqOohPn+KbIyVvj")
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (response.isSuccessful()) {
        String responseData = response.body().string();
        try {
          FileWriter file = new FileWriter(JsonURL.EBISUBAY);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    EbisuBayApiCall model = new EbisuBayApiCall();
    model.fetchData();
  }
}
