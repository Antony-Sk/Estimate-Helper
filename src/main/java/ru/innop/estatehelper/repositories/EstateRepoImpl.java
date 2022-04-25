package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.HouseEstate;
import main.java.ru.innop.estatehelper.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EstateRepoImpl implements EstateRepo {
    private final ArrayList<Estate> storage = new ArrayList<>();

    @Override
    public void saveEstate(Estate estate) {
        storage.add(estate);
    }

    @Override
    public Estate getEstateById(Long id) {
        return storage.get(Math.toIntExact(id));
    }

    @Override
    public void deleteEstate(Long id) {
        storage.remove(getEstateById(id));
    }

    @Override
    public void deleteEstate(Estate estate) {
        storage.remove(estate);
    }

    @Override
    public void updateEstate(Estate estate) {
        storage.set(Math.toIntExact(estate.getId()), estate);
    }

    @Override
    public List<Estate> findAllBySeller(User seller) {
        List<Estate> res = new ArrayList<>();
        for (Estate estate : storage) {
            if (estate.getSeller().equals(seller))
                res.add(estate);
        }
        return res;
    }

    @Override
    public List<Estate> findAll() {
        return storage;
    }

    @Override
    public List<Estate> sortByPrice() {
        return storage.stream().sorted(Comparator.comparing(Estate::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Estate> sortByAddress() {
        return storage.stream().sorted(Comparator.comparing(Estate::getAddress)).collect(Collectors.toList());
    }
}
