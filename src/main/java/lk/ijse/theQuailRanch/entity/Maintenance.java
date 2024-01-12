package lk.ijse.theQuailRanch.entity;

import java.sql.Date;

public class Maintenance {
    private String ttId;
    private String empId;
    private String nestId;
    private Date date;

    public Maintenance() {
    }

    public Maintenance(String ttId, String empId, String nestId, Date date) {
        this.ttId = ttId;
        this.empId = empId;
        this.nestId = nestId;
        this.date = date;
    }

    public String getTtId() {
        return ttId;
    }

    public void setTtId(String ttId) {
        this.ttId = ttId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getNestId() {
        return nestId;
    }

    public void setNestId(String nestId) {
        this.nestId = nestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
