package main.java.ru.innop.estatehelper.model;

public class HouseEstate extends Estate {
    private Integer countOfRooms;

    private String address;

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

    public HouseEstate(String description, User seller, Integer countOfRooms, String address, Integer spaceAmount) {
        super(description, seller, EstateType.HOUSE);
        this.countOfRooms = countOfRooms;
        this.address = address;
        this.spaceAmount = spaceAmount;
    }

    @Override
    public String toString() {
        return "HouseEstate{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                ", countOfRooms=" + countOfRooms +
                ", address='" + address + '\'' +
                ", spaceAmount=" + spaceAmount +
                '}';
    }
}
