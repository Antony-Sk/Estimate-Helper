package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.EstateType;
import main.java.ru.innop.estatehelper.model.HouseEstate;
import main.java.ru.innop.estatehelper.model.User;

public class EstateFactoryImpl implements EstateFactory {
    @Override
    public Estate createEstate(EstateType type, Object ... params) {
        Estate estate = null;
        switch (type) {
            case HOUSE:
                estate = new HouseEstate((String) params[0], (User) params[1], (String) params[2], (Integer) params[3], (Integer) params[4]);
            case BROKEN_PLANE: // TODO;
            case BUSINESS_CENTRE: // TODO;
        }
        return estate;
    }
}
