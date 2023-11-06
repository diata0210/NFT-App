import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.CoinDeskBlogModel;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Đường dẫn đến tệp JSON
            String filePath = "C:\\Users\\chubebanso\\Desktop\\Object Mapper\\project_oop\\src\\main\\java\\data\\CoinDeskBlog.json";

            // Đọc dữ liệu từ tệp JSON
            Gson gson = new Gson();
            Type listType = new TypeToken<List<CoinDeskBlogModel>>() {
            }.getType();
            List<CoinDeskBlogModel> blogData = gson.fromJson(new FileReader(filePath), listType);

            // Hiển thị dữ liệu lấy được
            for (CoinDeskBlogModel blogModel : blogData) {
                System.out.println("Title: " + blogModel.getTitle());
                System.out.println("Author: " + blogModel.getAuthor());
                System.out.println("Date: " + blogModel.getDate());
                System.out.println("Related Tags: " + blogModel.getRelatedTags());
                System.out.println("Description: " + blogModel.getDesc());
                System.out.println("-----------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
