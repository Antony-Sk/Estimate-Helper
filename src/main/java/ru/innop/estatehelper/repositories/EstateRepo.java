package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.Estate;

public interface EstateRepo {
    void saveEstate(Estate estate);

    Estate getEstateById(Long id);

    void deleteEstate(Long id);

    void deleteEstate(Estate estate);

    void updateEstate(Estate estate);
}
