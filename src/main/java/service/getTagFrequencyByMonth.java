package service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import repository.implement.BlogNFTicallyRepositoryImp;
import repository.implement.CoinDeskRepositoryImp;
import repository.implement.CrytoNewsBlogRepositoryImp;
import repository.implement.TheartNewsPaperRepositoryImp;

public class getTagFrequencyByMonth {
    public static void main(String[] args) {
        BlogNFTicallyRepositoryImp blogRepo = new BlogNFTicallyRepositoryImp();
        CoinDeskRepositoryImp coinDeskRepo = new CoinDeskRepositoryImp();
        CrytoNewsBlogRepositoryImp cryptoNewsRepo = new CrytoNewsBlogRepositoryImp();
        TheartNewsPaperRepositoryImp artNewsRepo = new TheartNewsPaperRepositoryImp();
        blogRepo.loadData();
        coinDeskRepo.loadData();
        cryptoNewsRepo.loadData();
        artNewsRepo.loadData();
        // Lấy tần suất tags từ các nguồn cho một tháng cụ thể
        String month = "08";
        Map<String, Integer> overallTagFrequency = new HashMap<>();
        mergeTagFrequency(overallTagFrequency, blogRepo.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, coinDeskRepo.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, cryptoNewsRepo.getTagFrequencyByMonth(month));
        mergeTagFrequency(overallTagFrequency, artNewsRepo.getTagFrequencyByMonth(month));

        // Sắp xếp và in ra top tags
        overallTagFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10) // Giới hạn 10 tags hàng đầu
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private static void mergeTagFrequency(Map<String, Integer> overallTagFrequency, Map<String, Integer> tagFrequency) {
        for (Map.Entry<String, Integer> entry : tagFrequency.entrySet()) {
            overallTagFrequency.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }
}