package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.*;

public class EstateHouseFactory implements EstateFactory {
    @Override
    public Estate createEstate(Object... params) {
        return new HouseEstate((String) params[0],
                (User) params[1],
                (String) params[2],
                (Double) params[3],
                (Integer) params[4],
                (Integer) params[5]);
    }
}
