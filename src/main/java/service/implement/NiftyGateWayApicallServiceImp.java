package service.implement;
import repository.NiftyGateWayApiCallRepository;
import repository.implement.NiftyGateWayApiCallRepositoryImp;
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
}