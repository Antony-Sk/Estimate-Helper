package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.User;

import java.util.ArrayList;
import java.util.List;

public class EstateRepoImpl implements EstateRepo {
    private final ArrayList<Estate> storage = new ArrayList<>();

    @Override
    public void saveEstate(Estate estate) {
        storage.add(estate);
    }

    @Override
    public Estate getEstateById(Long id) {
        return null;
    }

    @Override
    public void deleteEstate(Long id) {

    }

    @Override
    public void deleteEstate(Estate estate) {

    }

    @Override
    public void updateEstate(Estate estate) {

    }

    @Override
    public List<Estate> findAllBySeller(User seller) {
        List<Estate> res = new ArrayList<>();
        for (Estate estate: storage) {
            if (estate.getSeller().equals(seller))
                res.add(estate);
        }
        return res;
    }
}
