    package repository.implement;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import models.NiftyGateWayApiCallModel;
import models.PlazaNFTModel;
import repository.NiftyGateWayApiCallRepository;
import repository.Repository;
    public class NiftyGateWayApiCallRepositoryImp  implements NiftyGateWayApiCallRepository,Repository{
        public static NiftyGateWayApiCallRepositoryImp instance;
        private List<NiftyGateWayApiCallModel> models = new ArrayList<>();

        public static NiftyGateWayApiCallRepositoryImp getInstance() {
            if (instance == null)
                instance = new NiftyGateWayApiCallRepositoryImp();
            return instance;
        }

        public void loadData() {
            try {
                ObjectMapper mapper = new ObjectMapper();
                File jsonFile = new File("src/main/java/data/NiftyGateWay.json"); // Đổi đường dẫn tới tệp JSON cục bộ của bạn
                JsonNode rootNode = mapper.readTree(jsonFile);
                JsonNode dataNode = rootNode.get("data");
                JsonNode resultsNode = dataNode.get("results");
                if (resultsNode.isArray()) {
                    for (JsonNode node : resultsNode) {
                        NiftyGateWayApiCallModel model = new NiftyGateWayApiCallModel();

                        model.setId(node.get("id").asInt());
                        model.setNiftyType(node.get("niftyType").asInt());
                        model.setCreatorName(node.get("creatorName").asText());
                        model.setNiftyTitle(node.get("niftyTitle").asText());
                        models.add(model);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List<NiftyGateWayApiCallModel> getAllModels() {
            return models;
        }

        public List<String> getNFTsByAuthor(String author) {
            List<String> nftsByAuthor = new ArrayList<>();
            for (NiftyGateWayApiCallModel model : models) {
                if (model.getCreatorName().equalsIgnoreCase(author)) {
                    nftsByAuthor.add(model.getId() + " - " + model.getNiftyType()+"-"+model.getNiftyTitle());
                }
            }
            return nftsByAuthor;
        }
        public List<NiftyGateWayApiCallModel> getArticlesByTitle(String title) {
        List<NiftyGateWayApiCallModel> matchingArticles = new ArrayList<>();
        for (NiftyGateWayApiCallModel model : models) {
            if (model.getNiftyTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingArticles.add(model);
            }
        }
        return matchingArticles;
    }

        public static void main(String[] args) {
            NiftyGateWayApiCallRepositoryImp nifty =new NiftyGateWayApiCallRepositoryImp();

            nifty.loadData();

            String authorName = "FAN3"; 
            List<String> nftsByAuthor = nifty.getNFTsByAuthor(authorName);

            System.out.println("NFTs by " + authorName + ":");
            for (String nft : nftsByAuthor) {
                System.out.println(nft);
            }
        }
    }
