package com.rupeeboss.rba.core.model;

/**
 * Created by Rajeev Ranjan on 15/11/2016.
 */

public class LeadRequest {


    /**
     * Mobile : 9820533567
     * Name : Shubhangi Ganjale
     * EMail : s@gmail.com
     * city : Mumbai
     * Product : Home Loan
     * Remark : Testing Lead Capture
     * Loan_amnt : 400000.00
     * Monthly_income : 400000.00
     * profession : salaried
     * Company : Rupeeboss
     * Designation : developer
     * Status : 3
     * empCode : RB40000441
     * followupDate : 12-11-2016
     * AssgnId : 2
     */

    private int demoGiven;
    private int brokerId;
    private String Mobile;
    private String Name;
    private String EMail;
    private String city;
    private String Product;
    private String Remark;
    private String Loan_amnt;
    private String Monthly_income;
    private String profession;
    private String Company;
    private String Designation;
    private int Status;

    public String getExpctDisbsDate() {
        return ExpctDisbsDate;
    }

    public void setExpctDisbsDate(String expctDisbsDate) {
        ExpctDisbsDate = expctDisbsDate;
    }

    private String empCode;
    private String followupDate;
    private String AssgnId;
    private String ExpctDisbsDate;

    public int getDemoGiven() {
        return demoGiven;
    }

    public void setDemoGiven(int demoGiven) {
        this.demoGiven = demoGiven;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getLoan_amnt() {
        return Loan_amnt;
    }

    public void setLoan_amnt(String Loan_amnt) {
        this.Loan_amnt = Loan_amnt;
    }

    public String getMonthly_income() {
        return Monthly_income;
    }

    public void setMonthly_income(String Monthly_income) {
        this.Monthly_income = Monthly_income;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getAssgnId() {
        return AssgnId;
    }

    public void setAssgnId(String AssgnId) {
        this.AssgnId = AssgnId;
    }
}
