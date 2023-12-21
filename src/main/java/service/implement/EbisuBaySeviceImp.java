package service.implement;

import java.util.List;

import models.EbisuBayModel;
import repository.EbisuBayRepository;
import repository.implement.EbisuBayRepositoryImp;
import service.EbisuBayService;

public class EbisuBaySeviceImp implements EbisuBayService {
    private EbisuBayRepository EbisuBayRepository = EbisuBayRepositoryImp.getInstance();

    public static EbisuBaySeviceImp instance;

    public static EbisuBaySeviceImp getInstance() {
        if (instance == null)
            instance = new EbisuBaySeviceImp();
        return instance;
    }

    private EbisuBaySeviceImp() {
        EbisuBayRepository.loadData(); // Gọi loadData ở đây
    }

    @Override
    public List<EbisuBayModel> findModelsByName(String name) {
        return EbisuBayRepository.findModelsByName(name);
    }

    public List<EbisuBayModel> getAllModels() {
        return EbisuBayRepository.getAllModels();
    }

}
