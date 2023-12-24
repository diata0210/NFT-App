package crawler;

import java.io.FileWriter;
import java.io.IOException;
import data.util.JsonURL;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MintedApiCall implements BaseCrawler {

  @Override
  public void crawlData() {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType,
        "{\"query\":\"query getCollections($sort: CollectionSortInput, $filter: CollectionFilterInput, $after: String, $first: Int!, $address: EvmAddress, $isConnected: Boolean!) {\\n  collections(first: $first, filter: $filter, sort: $sort, after: $after) {\\n    edges {\\n      node {\\n        ...CollectionDetailFields\\n        ...CollectionPriceFields\\n        ...CollectionVolumeFields\\n        userOwnershipStats(user: $address) @include(if: $isConnected) {\\n          ...UserOwnershipStatsFields\\n          __typename\\n        }\\n        listingRewardStats {\\n          ...ListingRewardStatsFields\\n          __typename\\n        }\\n        __typename\\n      }\\n      cursor\\n      __typename\\n    }\\n    pageInfo {\\n      ...PageInfoFields\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\\nfragment CollectionDetailFields on AssetCollection {\\n  ...CollectionIdentifyFields\\n  name\\n  logo {\\n    url\\n    __typename\\n  }\\n  banner {\\n    url\\n    __typename\\n  }\\n  creator {\\n    ...UserFields\\n    __typename\\n  }\\n  description\\n  assetCount\\n  ownerCount\\n  raritySource\\n  isRarityEnable\\n  isCollectionOfferEnable\\n  listedCount\\n  analyticsEnable\\n  __typename\\n}\\n\\nfragment CollectionIdentifyFields on AssetCollection {\\n  address\\n  name\\n  chain {\\n    name\\n    __typename\\n  }\\n  status\\n  __typename\\n}\\n\\nfragment UserFields on UserAccount {\\n  evmAddress\\n  name\\n  avatar {\\n    url\\n    __typename\\n  }\\n  nonce\\n  __typename\\n}\\n\\nfragment CollectionPriceFields on AssetCollection {\\n  floorPrice {\\n    latestFloorPrice\\n    latestFloorPriceNative\\n    latestGlobalFloorPriceNative\\n    change24h\\n    change7d\\n    change30d\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment CollectionVolumeFields on AssetCollection {\\n  volume {\\n    volume7d\\n    volume24h\\n    volume30d\\n    volumeAll\\n    globalVolume7dNative\\n    globalVolume24hNative\\n    globalVolume30dNative\\n    globalVolumeAllNative\\n    volume7dNative\\n    volume24hNative\\n    volume30dNative\\n    volumeAllNative\\n    change24h\\n    change7d\\n    change30d\\n    __typename\\n  }\\n  __typename\\n}\\n\\nfragment UserOwnershipStatsFields on UserOwnershipStats {\\n  listed\\n  owned\\n  __typename\\n}\\n\\nfragment ListingRewardStatsFields on ListingRewardStats {\\n  referencePrice\\n  points\\n  isWhitelist\\n  __typename\\n}\\n\\nfragment PageInfoFields on PageInfo {\\n  hasPreviousPage\\n  hasNextPage\\n  startCursor\\n  endCursor\\n  __typename\\n}\",\"variables\":{\"first\":50,\"filter\":{\"chains\":[\"CRONOS\",\"ETHEREUM\"],\"verifiedOnly\":true},\"sort\":{\"field\":\"VOLUME_ONE_DAY\",\"isAscending\":false},\"isConnected\":false}}");
    Request request = new Request.Builder()
        .url("https://api.minted.network/graphql")
        .method("POST", body)
        .addHeader("authority", "api.minted.network")
        .addHeader("accept", "*/*")
        .addHeader("accept-language", "en-US,en;q=0.9")
        .addHeader("apollographql-client-name", "minted-ui-prod")
        .addHeader("apollographql-client-version", "current")
        .addHeader("authorization", "")
        .addHeader("content-type", "application/json")
        .addHeader("origin", "https://minted.network")
        .addHeader("referer", "https://minted.network/")
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
          FileWriter file = new FileWriter(JsonURL.MINTED);
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
    MintedApiCall model = new MintedApiCall();
    model.fetchData();
  }
}
