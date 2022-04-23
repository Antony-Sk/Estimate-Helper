package main.java.ru.innop.estatehelper.model;

public class FLatEstate extends Estate {
    private int numberOfResidents;

    public FLatEstate(String description, User seller, String address, Double price, Integer numberOfPeopleWhoAlreadyLivesHere) {
        super(description, seller, EstateType.FLAT, address, price);
        this.numberOfResidents = numberOfPeopleWhoAlreadyLivesHere;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    @Override
    public String toString() {
        return "FLat : " +
                "\n * description = '" + description + '\'' +
                "\n * seller = " + seller +
                "\n * type = " + type +
                "\n * address = '" + address + '\'' +
                "\n * price = " + price +
                "\n * number of residents = " + numberOfResidents;
    }
}
