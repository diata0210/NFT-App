package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpyoBlogModel extends UpyoModel implements CustomModel {

    public UpyoBlogModel(String title, String author, String date, String url, List<String> listContent){
        super.title = title;
        super.author = author;
        super.date = date;
        super.url = url;
        super.listContent = listContent;
    }

    public UpyoBlogModel(){
        super.title = null;
        super.author = null;
        super.date = null;
        super.url = null;
        super.listContent = null;
    }

    @Override
    public Map<String, Object> MapDescription() {
        Map<String, Object> res = new HashMap<>();
        res.put("title", title);
        res.put("author", author);
        res.put("date", date);
        res.put("url", url);
        res.put("contentList", listContent);
        return res;
    }
    
}
