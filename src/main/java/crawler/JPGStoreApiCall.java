package crawler;

import java.io.FileWriter;
import java.io.IOException;

import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JPGStoreApiCall implements ApiDataProvider {

    @Override
    public void fetchData() {
        OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
        .url("https://server.jpgstoreapis.com/analytics/topVolume?timeframe=24h&limit=40&page=5")
        .method("GET", null)
        .addHeader("authority", "server.jpgstoreapis.com")
        .addHeader("accept", "application/json, text/plain, */*")
        .addHeader("accept-language", "en-US,en;q=0.9")
        .addHeader("access-token", "")
        .addHeader("origin", "https://www.jpg.store")
        .addHeader("referer", "https://www.jpg.store/")
        .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Microsoft Edge\";v=\"120\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader("sec-ch-ua-platform", "\"Windows\"")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-site", "cross-site")
        .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
        .addHeader("x-jpgstore-csrf-protection", "1")
        .addHeader("Cookie", "GAESA=CoYBMDA4NzU5OWQ0MmZmNTMzN2MxZGRiYTM4Y2ZiMzJlOWM3OTA3NDRmZTA2ZDU5YzA5ZjAyMTI5NDFmZTU1M2ZjYTVmYThmNGZkMDAwODVjMTMxMDk2NDNlZDcxZTZiMzE0ZWZkMWE3Y2ZlNGJiOGEwZTk1ZDY5MzY1OTlkMTIyYWM4YWFhMzQQo6Dflcgx")
        .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                try {
                    FileWriter file = new FileWriter(JsonURL.JPG);
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
        JPGStoreApiCall model = new JPGStoreApiCall();
        model.fetchData();
      }
}
