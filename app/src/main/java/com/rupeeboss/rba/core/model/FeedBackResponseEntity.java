package com.rupeeboss.rba.core.model;

public class FeedBackResponseEntity {
    /**
     * Assignee : 240
     * Date : 30-11-2016
     * Name : Sameer Naik
     * Product : 7
     * Remark : Done....
     * Status : 23
     * time : 0:21
     */

    private int Assignee;
    private String Date;
    private String Name;
    private int Product;
    private String Remark;
    private int Status;
    private String time;

    public int getAssignee() {
        return Assignee;
    }

    public void setAssignee(int Assignee) {
        this.Assignee = Assignee;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getProduct() {
        return Product;
    }

    public void setProduct(int Product) {
        this.Product = Product;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}