package crawler;

import java.io.FileWriter;
import java.io.IOException;

import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImmutableApiCall implements BaseCrawler {

  @Override
  public void crawlData() {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
        .url("https://marketplace-api.immutable.com/v1/collections?page_size=40&order_by=volume_24h&direction=desc")
        .method("GET", null)
        .addHeader("authority", "marketplace-api.immutable.com")
        .addHeader("accept", "*/*")
        .addHeader("accept-language", "en-US,en;q=0.9")
        .addHeader("origin", "https://market.immutable.com")
        .addHeader("referer", "https://market.immutable.com/")
        .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Microsoft Edge\";v=\"120\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader("sec-ch-ua-platform", "\"Windows\"")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-site", "same-site")
        .addHeader("user-agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (response.isSuccessful()) {
        String responseData = response.body().string();
        try {
          FileWriter file = new FileWriter(JsonURL.IMMUTABLE);
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
    ImmutableApiCall model = new ImmutableApiCall();
    model.fetchData();
  }
}