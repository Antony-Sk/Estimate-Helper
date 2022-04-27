package main.java.ru.innop.estatehelper.model;

import java.text.DecimalFormat;

public class FLatEstate extends Estate {
    private int numberOfResidents;

    public FLatEstate(String description, User seller, String address, Double price, Integer numberOfResidents) {
        super(description, seller, address, price);
        this.numberOfResidents = numberOfResidents;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "FLat : " +
                "\n * description = '" + getDescription() + '\'' +
                "\n * seller = " + getSeller() +
                "\n * address = '" + getAddress() + '\'' +
                "\n * price = " + df.format(getPrice()) + " rubbles" +
                "\n * number of residents = " + numberOfResidents + "\n";
    }
}
