package service.implement;

import java.util.List;
import models.PlazaNFTModel;
import repository.PlazaNFTRepository;
import repository.implement.PlazaNFTRepositoryImp;
import service.PlazaNFTService;

public class PlazaNFTServiceImp implements PlazaNFTService {
    private static PlazaNFTServiceImp instance;
    private PlazaNFTRepository plazaNFTRepository = PlazaNFTRepositoryImp.getInstance();

    public static PlazaNFTServiceImp getInstance() {
        if (instance == null)
            instance = new PlazaNFTServiceImp();
        return instance;
    }

    @Override
    public List<PlazaNFTModel> getAllModels() {
        return plazaNFTRepository.getAllModels();
    }

    @Override
    public List<String> getNFTsByTags(String tag) {
        return plazaNFTRepository.getNFTsByTags(tag);
    }

    @Override
    public List<String> getTagsByDate(String date) {
        return plazaNFTRepository.getTagsByDate(date);
    }

    @Override
    public List<String> getTagsByWeek(String startDate) {
        return plazaNFTRepository.getTagsByWeek(startDate);
    }

    @Override
    public List<String> getTagsByMonth(String month) {
        return plazaNFTRepository.getTagsByMonth(month);
    }

    public static void main(String[] args) {

    }
}
