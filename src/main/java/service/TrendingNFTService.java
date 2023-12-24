package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.ApiModel;
import models.BlogModel;
import java.util.LinkedHashMap;

public class TrendingNFTService {

  public Map<ApiModel, Integer> getTrendingNFTsWithTagCount() {
    List<ApiModel> nfts = GetAllNft.allNft();
    List<BlogModel> blogArticles = GetArticles.allArticles();
    Map<ApiModel, Integer> nftToTagMatchCount = new HashMap<>();

    for (ApiModel nft : nfts) {
      int tagMatchCount = 0;
      String nftDescription = nft.getDescription().toLowerCase();

      for (BlogModel blog : blogArticles) {
        for (String tag : blog.getRelatedTags()) {
          if (nftDescription.contains(tag.toLowerCase())) {
            tagMatchCount++;
          }
        }
      }

      if (tagMatchCount > 0) {
        nftToTagMatchCount.put(nft, tagMatchCount);
      }
    }
    return nftToTagMatchCount.entrySet()
        .stream()
        .sorted(Map.Entry.<ApiModel, Integer>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new));
  }
}
