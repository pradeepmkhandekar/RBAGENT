package com.rupeeboss.rba.core_loan_fm.model;

public  class NewLoanApplicationEnity {
        /**
         * Id : 9
         * Name : test nitin
         * LoanAmount : 700000
         * Status : null
         * BankId : 20
         * BankUrl :
         * FBAID : 1976
         * ProductType : PSL
         * MobileNo : 9874524324
         * BankName : null
         * IsActive : 1
         * LeadId : 1438350
         * statusupdatedate : null
         * ApplDate : 18/03/2019
         * RBStatusDesc : null
         * StatusPercent : 0
         * bank_image : http://erp.rupeeboss.com/Banklogo/hdfc.png
         */

        private int Id;
        private String Name;
        private String LoanAmount;


        private String RBStatus;
        private int BankId;
        private String BankUrl;
        private int FBAID;
        private String ProductType;
        private String MobileNo;
        private String BankName;
        private int IsActive;
        private String LeadId;
        private String statusupdatedate;
        private String ApplDate;
        private String RBStatusDesc;
        private String StatusPercent;
        private String bank_image;



       private String progress_image;
    private String   Bank_URL;
    public String getBank_URL() {
        return Bank_URL;
    }

    public void setBank_URL(String bank_URL) {
        Bank_URL = bank_URL;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getRBStatus() {
        return RBStatus;
    }

    public void setRBStatus(String RBStatus) {
        this.RBStatus = RBStatus;
    }

    public int getBankId() {
        return BankId;
    }

    public void setBankId(int bankId) {
        BankId = bankId;
    }

    public String getBankUrl() {
        return BankUrl;
    }

    public void setBankUrl(String bankUrl) {
        BankUrl = bankUrl;
    }

    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getLeadId() {
        return LeadId;
    }

    public void setLeadId(String leadId) {
        LeadId = leadId;
    }

    public String getStatusupdatedate() {
        return statusupdatedate;
    }

    public void setStatusupdatedate(String statusupdatedate) {
        this.statusupdatedate = statusupdatedate;
    }

    public String getApplDate() {
        return ApplDate;
    }

    public void setApplDate(String applDate) {
        ApplDate = applDate;
    }

    public String getRBStatusDesc() {
        return RBStatusDesc;
    }

    public void setRBStatusDesc(String RBStatusDesc) {
        this.RBStatusDesc = RBStatusDesc;
    }

    public String getStatusPercent() {
        return StatusPercent;
    }

    public void setStatusPercent(String statusPercent) {
        StatusPercent = statusPercent;
    }

    public String getBank_image() {
        return bank_image;
    }

    public void setBank_image(String bank_image) {
        this.bank_image = bank_image;
    }

    public String getProgress_image() {
        return progress_image;
    }

    public void setProgress_image(String progress_image) {
        this.progress_image = progress_image;
    }

}