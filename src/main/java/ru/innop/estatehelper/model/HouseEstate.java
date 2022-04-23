package main.java.ru.innop.estatehelper.model;

public class HouseEstate extends Estate {
    private Integer countOfRooms;

    private Integer spaceAmount; // in sq ft

    public Integer getSpaceAmount() {
        return spaceAmount;
    }

    public void setSpaceAmount(Integer spaceAmount) {
        this.spaceAmount = spaceAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCountOfRooms() {
        return countOfRooms;
    }

    public void setCountOfRooms(Integer countOfRooms) {
        this.countOfRooms = countOfRooms;
    }

    public HouseEstate(String description, User seller, String address, Double price, Integer countOfRooms, Integer spaceAmount) {
        super(description, seller, EstateType.HOUSE, address, price);
        this.countOfRooms = countOfRooms;
        this.spaceAmount = spaceAmount;
    }

    @Override
    public String toString() {
        return "House : " +
                "\n * description = '" + description + '\'' +
                "\n * seller = " + seller +
                "\n * countOfRooms = " + countOfRooms +
                "\n * address = '" + address + '\'' +
                "\n * spaceAmount = " + spaceAmount;
    }
}
