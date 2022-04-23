package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.model.VillaEstate;

public class EstateVillaFactory implements EstateFactory{
    @Override
    public Estate createEstate(Object... params) {
        return new VillaEstate((String) params[0],
                (User) params[1],
                (String) params[2],
                (Double) params[3],
                (Integer) params[4],
                (Boolean) params[5],
                (Integer) params[6]);
    }
}
