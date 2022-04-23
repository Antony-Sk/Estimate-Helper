package main.java.ru.innop.estatehelper.model;

public class Villa extends Estate {
    private int numberOfPools;
    private boolean bowling;
    private int numberOfHelicopters;

    public boolean isBowling() {
        return bowling;
    }

    public void setBowling(boolean bowling) {
        this.bowling = bowling;
    }

    public int getNumberOfHelicopters() {
        return numberOfHelicopters;
    }

    public void setNumberOfHelicopters(int numberOfHelicopters) {
        this.numberOfHelicopters = numberOfHelicopters;
    }

    public int getNumberOfPools() {
        return numberOfPools;
    }

    public void setNumberOfPools(int numberOfPools) {
        this.numberOfPools = numberOfPools;
    }

    public Villa(String description, User seller, String address, Double price, int numberOfPools, boolean bowling, int numberOfHelicopters) {
        super(description, seller, EstateType.VILLA, address, price);
        this.bowling = bowling;
        this.numberOfHelicopters = numberOfHelicopters;
        this.numberOfPools = numberOfPools;
    }
}
