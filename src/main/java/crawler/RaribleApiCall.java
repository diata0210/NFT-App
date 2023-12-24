package crawler;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RaribleApiCall implements BaseCrawler {

  public void crawlData(){
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType,
        "{\"continuation\":\"eyJwYXJhbXMiOls2LjY3NzI0NjkzNjgsIjB4MWI4MjliOTI2YTE0NjM0ZDM2NjI1ZTYwMTY1YzA3NzBjMDlkMDJiMiJdfQ==\",\"size\":20,\"filter\":{\"verifiedOnly\":false,\"sort\":\"VOLUME_DESC\",\"blockchains\":[\"ETHEREUM\"],\"showInRanking\":false,\"period\":\"DAY\",\"hasCommunityMarketplace\":false,\"currency\":\"NATIVE\"}}");
    Request request = new Request.Builder()
        .url("https://rarible.com/marketplace/api/v4/collections/search")
        .method("POST", body)
        .addHeader("authority", "rarible.com")
        .addHeader("accept", "*/*")
        .addHeader("accept-language", "vi,en;q=0.9,en-GB;q=0.8,en-US;q=0.7")
        .addHeader("content-type", "application/json")
        .addHeader("cookie",
            "_gcl_au=1.1.1740575862.1699103202; _scid=8bd5a1e4-e7d6-4514-be4d-da24bff8ab1f; _rdt_uuid=1699103202386.c7e01b90-c703-44f3-a7ce-8d1b08f4af39; ajs_anonymous_id=4b487f86-1149-496e-ab87-f03dff174773; _sctr=1%7C1699030800000; _hjSessionUser_2596294=eyJpZCI6IjEyNWNhNjYwLTI2Y2EtNTIxYy04YWViLWZiZmQ0MDA3OGQ1YSIsImNyZWF0ZWQiOjE2OTkxMDMyMDI1OTAsImV4aXN0aW5nIjp0cnVlfQ==; _gid=GA1.2.1982175551.1699285158; _hjIncludedInSessionSample_2596294=0; _hjSession_2596294=eyJpZCI6ImQwNDkxMjdkLTk5ZDktNDNjOS05OTQ4LTU0ODI4NzEzMmEwMSIsImNyZWF0ZWQiOjE2OTkyOTI3MjQyNTAsImluU2FtcGxlIjpmYWxzZSwic2Vzc2lvbml6ZXJCZXRhRW5hYmxlZCI6dHJ1ZX0=; _hjAbsoluteSessionInProgress=0; _gat=1; _ga_QPJ7KC6DS2=GS1.1.1699292723.6.1.1699292829.54.0.0; _ga=GA1.1.1938631099.1699103202; _uetsid=a5b5c1b07cba11ee8a38ede8d80a407b; _uetvid=ffb3d5307b1211ee8a3685a8bfc7fafd; _scid_r=8bd5a1e4-e7d6-4514-be4d-da24bff8ab1f; __kla_id=eyJjaWQiOiJNelV6TmpreE5qY3RNRFpsT0MwME4yTTJMVGxsTlRFdE5tRTNOV0poWXpFeU5UTXkiLCIkcmVmZXJyZXIiOnsidHMiOjE2OTkxMDMyMDMsInZhbHVlIjoiaHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8iLCJmaXJzdF9wYWdlIjoiaHR0cHM6Ly9yYXJpYmxlLmNvbS8ifSwiJGxhc3RfcmVmZXJyZXIiOnsidHMiOjE2OTkyOTI4MzAsInZhbHVlIjoiIiwiZmlyc3RfcGFnZSI6Imh0dHBzOi8vcmFyaWJsZS5jb20vIn19; _ga_HENWSLZ89C=GS1.2.1699292724.5.1.1699292830.60.0.0")
        .addHeader("origin", "https://rarible.com")
        .addHeader("referer", "https://rarible.com/explore/ethereum/collections")
        .addHeader("sec-ch-ua", "\"Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader("sec-ch-ua-platform", "\"Windows\"")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-site", "same-origin")
        .addHeader("user-agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
        .addHeader("x-fingerprint", "cdb78841ecb355beffa366074e4f2af2")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (response.isSuccessful()) {
        String responseData = response.body().string();
        System.out.println(responseData);
        try {
          FileWriter file = new FileWriter(JsonURL.RARIBLE);
          file.write(responseData);
          file.flush();
          file.close();
          System.out.println("JSON data has been successfully saved to the file.");
        } catch (JSONException | IOException e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("Request failed with code: " + response.code());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    RaribleApiCall model = new RaribleApiCall();
    model.fetchData();
  }
}
