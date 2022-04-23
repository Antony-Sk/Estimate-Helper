package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.Estate;

public interface EstateFactory {
    Estate createEstate(Object... params);
}
