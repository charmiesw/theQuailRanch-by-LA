package lk.ijse.theQuailRanch.entity;

import java.sql.Date;

public class Salary {
    private String salId;
    private String empId;
    private String Amount;
    private Date paidDate;

    public Salary() {
    }

    public Salary(String salId, String empId, String amount, Date paidDate) {
        this.salId = salId;
        this.empId = empId;
        Amount = amount;
        this.paidDate = paidDate;
    }

    public String getSalId() {
        return salId;
    }

    public void setSalId(String salId) {
        this.salId = salId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
}
