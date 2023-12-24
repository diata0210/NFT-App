package crawler;

import java.io.FileWriter;
import java.io.IOException;
import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MooarApiCall implements BaseCrawler {

  @Override
  public void crawlData() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder()
          .build();
      MediaType mediaType = MediaType.parse("application/json");
      RequestBody body = RequestBody.create(mediaType,
          "{\"query\":\"query queryCollections($filter: CollectionStatsFilterInput!) {\\n  collectionStats(filter: $filter) {\\n    collections {\\n      name\\n      contractAddress\\n      chain\\n      featureImage\\n      supply\\n      profile\\n      hasBuyNow\\n      floorPrice {\\n        ...TokenAmount\\n      }\\n      owners\\n      listingCount\\n      lastSaleAt\\n      trades\\n      volume {\\n        ...TokenAmount\\n      }\\n      oneDayVolume {\\n        ...TokenAmount\\n      }\\n      oneDayVolumeChange\\n      oneMonthVolume {\\n        ...TokenAmount\\n      }\\n      oneMonthVolumeChange\\n      oneWeekVolume {\\n        ...TokenAmount\\n      }\\n      oneWeekVolumeChange\\n      slug\\n    }\\n    hasMore\\n    limit\\n    offset\\n  }\\n}\\n\\nfragment TokenAmount on TokenAmount {\\n  token\\n  amount\\n  usd\\n}\",\"variables\":{\"filter\":{\"hasBuyNow\":false,\"chain\":\"polygon\",\"sortType\":\"oneDayVolume\",\"sortOrder\":\"desc\",\"limit\":100,\"offset\":0}}}");
      Request request = new Request.Builder()
          .url("https://market2.mooar.com/graphql")
          .method("POST", body)
          .addHeader("authority", "market2.mooar.com")
          .addHeader("accept", "*/*")
          .addHeader("accept-language", "en-US,en;q=0.9")
          .addHeader("content-type", "application/json")
          .addHeader("origin", "https://www.mooar.com")
          .addHeader("referer", "https://www.mooar.com/")
          .addHeader("sec-ch-ua",
              "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Microsoft Edge\";v=\"120\"")
          .addHeader("sec-ch-ua-mobile", "?0")
          .addHeader("sec-ch-ua-platform", "\"Windows\"")
          .addHeader("sec-fetch-dest", "empty")
          .addHeader("sec-fetch-mode", "cors")
          .addHeader("sec-fetch-site", "same-site")
          .addHeader("user-agent",
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
          .addHeader("Cookie",
              "__cf_bm=uHPkMzWu97J2L7860LFNtwEG6E2KZXUcOzTXsOGL4e8-1702479777-1-ASD78cyhxGPE7OqRhIB8+zesmlHgq/ldZX0rp7s3ZfratWGmNIZBhGxRaQshj+K7C4vxngyOrNBM6u2iiOYTuDU=")
          .build();

      try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
          String responseData = response.body().string();
          try {
            FileWriter file = new FileWriter(JsonURL.MOOAR);
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
    MooarApiCall model = new MooarApiCall();
    model.crawlData();
  }
}
