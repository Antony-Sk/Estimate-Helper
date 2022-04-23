package main.java.ru.innop.estatehelper.model;

public class FLatEstate extends Estate {
    private int numberOfPeopleWhoAlreadyLivesHere;

    public FLatEstate(String description, User seller, String address, Double price, Integer numberOfPeopleWhoAlreadyLivesHere) {
        super(description, seller, EstateType.FLAT, address, price);
        this.numberOfPeopleWhoAlreadyLivesHere = numberOfPeopleWhoAlreadyLivesHere;
    }
}
