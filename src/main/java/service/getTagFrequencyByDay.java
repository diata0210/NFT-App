package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class getTagFrequencyByDay {
    
    public static Map<String, Integer> getTagFrequencyByDay(String day) {
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();

        Map<String, Integer> overallTagFrequency = new HashMap<>();
        mergeTagFrequency(overallTagFrequency, blogService.getTagFrequencyByDay(day));
        mergeTagFrequency(overallTagFrequency, coinDeskService.getTagFrequencyByDay(day));
        mergeTagFrequency(overallTagFrequency, cryptoNewsService.getTagFrequencyByDay(day));
        mergeTagFrequency(overallTagFrequency, artNewsService.getTagFrequencyByDay(day));

        return getTopTags(overallTagFrequency, 10); // Trả về top 10 tags
    }

    private static void mergeTagFrequency(Map<String, Integer> overallTagFrequency, Map<String, Integer> tagFrequency) {
        for (Map.Entry<String, Integer> entry : tagFrequency.entrySet()) {
            overallTagFrequency.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static Map<String, Integer> getTopTags(Map<String, Integer> tagFrequency, int limit) {
        return tagFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, 
                        Map.Entry::getValue, 
                        (e1, e2) -> e1, 
                        LinkedHashMap::new));
    }

    public static void main(String[] args) {
        String day = "08-01";
        Map<String, Integer> topTags = getTagFrequencyByDay(day);
        topTags.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
