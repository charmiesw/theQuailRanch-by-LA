package lk.ijse.theQuailRanch.entity;

public class Nest {
    private String id;
    private String category;
    private String  amountOfBirds;

    public Nest() {
    }

    public Nest(String id, String category, String amountOfBirds) {
        this.id = id;
        this.category = category;
        this.amountOfBirds = amountOfBirds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmountOfBirds() {
        return amountOfBirds;
    }

    public void setAmountOfBirds(String amountOfBirds) {
        this.amountOfBirds = amountOfBirds;
    }
}
