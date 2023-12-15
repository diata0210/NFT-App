package service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import service.implement.BlogNFTicallyServiceImp;
import service.implement.CoinDeskServiceImp;
import service.implement.CrytoNewsBlogServiceImp;
import service.implement.PlazaNFTServiceImp;
import service.implement.TheartNewsPaperServiceImp;

public class getTagFrequencyByMonth {

    public static Map<String, Integer> getTagFrequencyByMonth(String month) {
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        PlazaNFTService plazaNFTService = PlazaNFTServiceImp.getInstance();
        Map<String, Integer> overallTagFrequency = new HashMap<>();
        mergeTagFrequency(overallTagFrequency, blogService.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, coinDeskService.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, cryptoNewsService.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, artNewsService.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, plazaNFTService.getTagFrequencyByMonth(month));
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
        String month = "08";
        Map<String, Integer> topTags = getTagFrequencyByMonth(month);
        topTags.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
