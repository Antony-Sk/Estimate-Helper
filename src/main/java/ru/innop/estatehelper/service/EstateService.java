package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.User;

public interface EstateService {
    void sellEstate(User buyer, Estate estate);

    void loginNewEstate(Estate estate);

    void updateEstate(Estate estate);
}
