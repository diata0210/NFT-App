// package service.implement;

// import java.util.List;

// import models.TheartNewPaperBlogModel;
// import repository.TheartNewsPaperRepository;
// import repository.implement.TheartNewsPaperRepositoryImp;
// import service.TheartNewsPaperService;

// public class TheartNewsPaperServiceImp implements TheartNewsPaperService {
//     private static TheartNewsPaperServiceImp instance;
//     private TheartNewsPaperRepository theartRepository = TheartNewsPaperRepositoryImp.getInstance();

//     public static TheartNewsPaperServiceImp getInstance() {
//         if (instance == null)
//             instance = new TheartNewsPaperServiceImp();
//         return instance;
//     }

//     public TheartNewsPaperServiceImp() {

//     }

//     @Override
//     public List<TheartNewPaperBlogModel> getAllTheart() {
//         return theartRepository.getAllTheart();
//     }

//     @Override
//     public List<String> getArticleByTags(String tag) {
//         return theartRepository.getArticleByTags(tag);
//     }

//     @Override
//     public List<String> getTagsArticleByDay(String date) {
//         return theartRepository.getArticleByTags(date);
//     }

//     @Override
//     public List<String> getTagsArticleByMonth(String month) {
//         return theartRepository.getArticleByTags(month);
//     }

//     @Override
//     public List<String> getTagsArticleByWeek(String startDate) {
//         return theartRepository.getTagsArticleByWeek(startDate);

//     }
// }
