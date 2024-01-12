package lk.ijse.theQuailRanch.entity;

public class FarmStock {
    private String id;
    private String supId;
    private String category;
    private String quantity;

    public FarmStock() {
    }

    public FarmStock(String id, String supId, String category, String quantity) {
        this.id = id;
        this.supId = supId;
        this.category = category;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
