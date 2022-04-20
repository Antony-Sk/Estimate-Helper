package main.java.ru.innop.estatehelper.factory;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.EstateType;

public interface EstateFactory {
    Estate createEstate(EstateType type, Object ... params);
}
