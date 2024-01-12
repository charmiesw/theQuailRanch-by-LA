package lk.ijse.theQuailRanch.entity;

public class SellStock {
    private String id;
    private String category;
    private int quantity;
    private double unitPrice;

    public SellStock() {
    }

    public SellStock(String id, String category, int quantity, double unitPrice) {
        this.id = id;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
