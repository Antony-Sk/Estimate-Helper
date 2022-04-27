package main.java.ru.innop.estatehelper.model;

import java.text.DecimalFormat;

public class HouseEstate extends Estate {
    private Integer countOfRooms;

    private Integer spaceAmount; // in sq ft

    public Integer getSpaceAmount() {
        return spaceAmount;
    }

    public void setSpaceAmount(Integer spaceAmount) {
        this.spaceAmount = spaceAmount;
    }

    public Integer getCountOfRooms() {
        return countOfRooms;
    }

    public void setCountOfRooms(Integer countOfRooms) {
        this.countOfRooms = countOfRooms;
    }

    public HouseEstate(String description, User seller, String address, Double price, Integer countOfRooms, Integer spaceAmount) {
        super(description, seller, address, price);
        this.countOfRooms = countOfRooms;
        this.spaceAmount = spaceAmount;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "House : " +
                "\n * description = '" + getDescription() + '\'' +
                "\n * price = " + df.format(getPrice()) + " rubbles" +
                "\n * seller = " + getSeller() +
                "\n * countOfRooms = " + countOfRooms +
                "\n * address = '" + getAddress() + '\'' +
                "\n * spaceAmount = " + spaceAmount + "\n";
    }
}
