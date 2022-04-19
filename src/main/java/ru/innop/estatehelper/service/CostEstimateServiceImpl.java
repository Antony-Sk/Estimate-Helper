package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.Estate;
import main.java.ru.innop.estatehelper.model.HouseEstate;

public class CostEstimateServiceImpl implements CostEstimateService {
    @Override
    public Integer estimateCost(Estate estate) {
        int cost = 50000;
        switch (estate.getType()) {
            case HOUSE: {
                HouseEstate houseEstate = (HouseEstate) estate;
                cost += houseEstate.getSpaceAmount() * 1000;
                cost *= houseEstate.getCountOfRooms();
            }
        }
        cost = (int) ((double) cost * 1.2);
        return cost;
    }
}
