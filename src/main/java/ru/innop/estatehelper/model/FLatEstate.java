package main.java.ru.innop.estatehelper.model;

public class FLatEstate extends Estate {
    private int numberOfPeopleWhoAlreadyLivesHere;

    public FLatEstate(String description, User seller, String address, Double price, Integer numberOfPeopleWhoAlreadyLivesHere) {
        super(description, seller, EstateType.FLAT, address, price);
        this.numberOfPeopleWhoAlreadyLivesHere = numberOfPeopleWhoAlreadyLivesHere;
    }

    public int getNumberOfPeopleWhoAlreadyLivesHere() {
        return numberOfPeopleWhoAlreadyLivesHere;
    }

    public void setNumberOfPeopleWhoAlreadyLivesHere(int numberOfPeopleWhoAlreadyLivesHere) {
        this.numberOfPeopleWhoAlreadyLivesHere = numberOfPeopleWhoAlreadyLivesHere;
    }

    @Override
    public String toString() {
        return "FLat : " +
                "\n * description = '" + description + '\'' +
                "\n * seller = " + seller +
                "\n * type = " + type +
                "\n * address = '" + address + '\'' +
                "\n * price = " + price +
                "\n * number of people who already lives here = " + numberOfPeopleWhoAlreadyLivesHere;
    }
}
