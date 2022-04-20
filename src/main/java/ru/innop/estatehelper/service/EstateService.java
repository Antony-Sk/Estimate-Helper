package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.User;

import java.util.List;

public interface EstateService {
    void sellEstate(User buyer, Estate estate);

    void loginNewEstate(Estate estate);

    void updateEstate(Estate estate);

    List<Estate> getEstatesBySeller(User user);
}
