package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.Estate;

import java.util.ArrayList;

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
}
