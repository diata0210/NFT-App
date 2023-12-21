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
import service.implement.TwitterServiceImp;

public class GetTagFrequencyByDays {

    public static Map<String, Integer> getTagFrequencyByDay(String date) {
        BlogNFTicallyServiceImp blogService = BlogNFTicallyServiceImp.getInstance();
        CoinDeskServiceImp coinDeskService = CoinDeskServiceImp.getInstance();
        CrytoNewsBlogServiceImp cryptoNewsService = CrytoNewsBlogServiceImp.getInstance();
        TheartNewsPaperServiceImp artNewsService = TheartNewsPaperServiceImp.getInstance();
        TwitterServiceImp twitterService = TwitterServiceImp.getInstance();
        PlazaNFTService plazaNFTService = PlazaNFTServiceImp.getInstance();
        Map<String, Integer> overallTagFrequency = new HashMap<>();
        mergeTagFrequency(overallTagFrequency, blogService.getTagFrequencyByDay(date));
        mergeTagFrequency(overallTagFrequency, coinDeskService.getTagFrequencyByDay(date));
        mergeTagFrequency(overallTagFrequency, cryptoNewsService.getTagFrequencyByDay(date));
        mergeTagFrequency(overallTagFrequency, artNewsService.getTagFrequencyByDay(date));
        mergeTagFrequency(overallTagFrequency, plazaNFTService.getTagFrequencyByDay(date));
        mergeTagFrequency(overallTagFrequency, twitterService.getTagFrequencyByDay(date));
        return getTopTags(overallTagFrequency); 
    }

    private static void mergeTagFrequency(Map<String, Integer> overallTagFrequency, Map<String, Integer> tagFrequency) {
        for (Map.Entry<String, Integer> entry : tagFrequency.entrySet()) {
            overallTagFrequency.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static Map<String, Integer> getTopTags(Map<String, Integer> tagFrequency) {
        return tagFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

}
