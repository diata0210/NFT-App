package service.implement;

import java.util.List;
import java.util.Map;

import models.BlogNFTicallyModel;
import repository.BlogNFTicallyRepository;
import repository.NiftyGateWayApiCallRepository;
import repository.implement.BlogNFTicallyRepositoryImp;
import repository.implement.NiftyGateWayApiCallRepositoryImp;
import service.BlogNFTicallyService;
import service.NiftyGateWayAplicallService;

public class NiftyGateWayApicallServiceImp implements NiftyGateWayAplicallService {
    private NiftyGateWayApiCallRepository niftyGateWayApiCallRepository = NiftyGateWayApiCallRepositoryImp.getInstance();

    public static NiftyGateWayApicallServiceImp instance;

    public static NiftyGateWayApicallServiceImp getInstance() {
        if (instance == null)
            instance = new NiftyGateWayApicallServiceImp();
        return instance;
    }

    private NiftyGateWayApicallServiceImp() {
        niftyGateWayApiCallRepository.loadData(); // Gọi loadData ở đây
    }

   
    public List<String> getArticleByTitle(String title){
         return niftyGateWayApiCallRepository.getArticleByTitle(title);
    }

    public static void main(String[] args) {
    }
}
