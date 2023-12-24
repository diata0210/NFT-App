package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import models.ApiModel;
import models.BlogModel;
import java.util.LinkedHashMap;
public class NFTCollerateWithTags {

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

    public static void main(String[] args) {
        NFTCollerateWithTags trendingService = new NFTCollerateWithTags();
        Map<ApiModel, Integer> trendingNFTsWithTagCount = trendingService.getTrendingNFTsWithTagCount();

        System.out.println("Trending NFTs:");
        for (Map.Entry<ApiModel, Integer> entry : trendingNFTsWithTagCount.entrySet()) {
            ApiModel nft = entry.getKey();
            Integer tagCount = entry.getValue();
            System.out.println("Name: " + nft.getName() + ", Floor Price: " + nft.getFloorPrice() + ", Tag Count: " + tagCount);
        }
    }
}
