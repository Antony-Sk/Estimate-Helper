package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.repositories.EstateRepo;

import java.util.List;

public class EstateServiceImpl implements EstateService {
    private final EstateRepo estateRepo;

    public EstateServiceImpl(EstateRepo estateRepo) {
        this.estateRepo = estateRepo;
    }

    @Override
    public void sellEstate(User buyer, Estate estate) {
        // log
        estateRepo.deleteEstate(estate);
    }

    @Override
    public void loginNewEstate(Estate estate) {
        // log
        estateRepo.saveEstate(estate);
    }

    @Override
    public void updateEstate(Estate estate) {
        // log
        estateRepo.updateEstate(estate);
    }

    @Override
    public List<Estate> getEstatesBySeller(User user) {
        return estateRepo.findAllBySeller(user);
    }
}
