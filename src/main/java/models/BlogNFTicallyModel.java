package models;

import java.util.HashMap;
import java.util.Map;

public class BlogNFTicallyModel extends NFTicallyModel implements CustomModel {
    public BlogNFTicallyModel(String title, String author, String date, String tag){
        super.title = title;
        super.author = author;
        super.date = date;
        super.tag = tag;
    }

    public BlogNFTicallyModel(){
        super.title = null;
        super.author = null;
        super.date = null;
        super.tag = null;
    }


    @Override
    public Map<String, Object> MapDescription() {
        Map<String, Object> res = new HashMap<>();
        res.put("title", title);
        res.put("author", author);
        res.put("date", date);
        res.put("tag", tag);
        return res;
    }
    
}

