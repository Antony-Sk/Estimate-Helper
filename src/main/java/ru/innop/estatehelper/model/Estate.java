package main.java.ru.innop.estatehelper.model;

public abstract class Estate {
    protected Long id;
    protected String description;
    protected User seller;
    protected EstateType type;

    public Estate(String description, User seller, EstateType type) {
        this.description = description;
        this.seller = seller;
        this.type = type;
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

    public EstateType getType() {
        return type;
    }

    public void setType(EstateType type) {
        this.type = type;
    }
}
