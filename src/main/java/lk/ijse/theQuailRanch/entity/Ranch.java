package lk.ijse.theQuailRanch.entity;

import java.sql.Date;

public class Ranch {
    private String id;
    private Date date;
    private String category;
    private String amountOfBirds;

    public Ranch() {
    }

    public Ranch(String id, Date date, String category, String amountOfBirds) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amountOfBirds = amountOfBirds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
