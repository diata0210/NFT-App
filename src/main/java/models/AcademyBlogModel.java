// package models;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class AcademyBlogModel extends BaseModel implements CustomModel {
//     private List<String> listTitle;
//     private List<String> listAuthor;
//     private List<String> listDate;

//     public AcademyBlogModel(String author, String title, String date, String url, List<String> listAuthor, List<String> listTitle, List<String> listDate) {
//         super.title = title;
//         super.date = date;
//         if (author != null) {
//             super.author = author;
//         } else {
//             super.author = "Unknown";
//         }
//         super.url = url;
//         this.listTitle = listTitle;
//         this.listAuthor = listAuthor;
//         this.listDate = listDate;
//     }
    

//     public AcademyBlogModel() {
//         super.title = null;
//         super.author = null;
//         super.date = null;
//         super.url = null;

//         this.listTitle = null;
//         this.listAuthor = null;
//         this.listDate = null;
//     }

//     @Override
//     public Map<String, Object> MapDescription() {
//         Map<String, Object> res = new HashMap<>();
//         res.put("title", title);
//         res.put("author", author);
//         res.put("date", date);
//         res.put("listTitle", listTitle);
//         res.put("listAuthor", listAuthor);
//         res.put("listDate", listDate);

//         return res;
//     }

//     @Override
//     public String Url() {
//         return url;
//     }

// }
