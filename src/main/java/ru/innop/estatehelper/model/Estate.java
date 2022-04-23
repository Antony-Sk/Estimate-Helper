package main.java.ru.innop.estatehelper.model;

public abstract class Estate {
    protected Long id;
    protected String description;
    protected User seller;
    protected String address;
    protected Double price; // in rubbles

    public Estate(String description, User seller, String address, Double price) {
        this.description = description;
        this.seller = seller;
        this.address = address;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
