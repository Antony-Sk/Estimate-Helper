package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.FLatEstate;
import main.java.ru.innop.estatehelper.model.User;

public class EstateFlatFactory implements EstateFactory{
    @Override
    public Estate createEstate(Object... params) {
        return  new FLatEstate((String) params[0],
                (User) params[1],
                (String) params[2],
                (Double) params[3],
                (Integer) params[4]);
    }
}
