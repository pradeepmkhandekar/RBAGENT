package com.rupeeboss.rba.core.model;

public  class FollowUpEntity {

    /**
     * Name : rupeeboss
     * Remark :
     * Status : Future Requirement
     * mobileNo : 9856247706
     */

    private String Name;
    private String Remark;
    private String Status;
    private String mobileNo;
    private int leadId;

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}