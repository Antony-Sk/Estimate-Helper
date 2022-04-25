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
        return "House : " +
                "\n * description = '" + getDescription() + '\'' +
                "\n * price = '" + getPrice() + '\'' +
                "\n * seller = " + getSeller() +
                "\n * countOfRooms = " + countOfRooms +
                "\n * address = '" + getAddress() + '\'' +
                "\n * spaceAmount = " + spaceAmount + "\n";
    }
}
