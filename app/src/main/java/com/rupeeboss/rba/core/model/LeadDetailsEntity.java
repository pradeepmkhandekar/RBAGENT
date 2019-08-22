package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LeadDetailsEntity implements Parcelable {
    /**
     * custName : Rajeev
     * leadId : 253736
     * mobNo : 87075407764
     * status : Property Doc Pending
     */

    private String custName;
    private int leadId;
    private String mobNo;

    /**
     * status : Property Doc Pending
     */

    private String status;

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * ProdType :
     * loanAmnt : 358362
     */

    private String assigneeName;
    private String ProdType;
    private String loanAmnt;

    private String remark;
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProdType() {
        return ProdType;
    }

    public void setProdType(String ProdType) {
        this.ProdType = ProdType;
    }

    public String getLoanAmnt() {
        return loanAmnt;
    }

    public void setLoanAmnt(String loanAmnt) {
        this.loanAmnt = loanAmnt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.custName);
        dest.writeInt(this.leadId);
        dest.writeString(this.mobNo);
        dest.writeString(this.status);
        dest.writeString(this.assigneeName);
        dest.writeString(this.ProdType);
        dest.writeString(this.loanAmnt);
    }

    public LeadDetailsEntity() {
    }

    protected LeadDetailsEntity(Parcel in) {
        this.custName = in.readString();
        this.leadId = in.readInt();
        this.mobNo = in.readString();
        this.status = in.readString();
        this.assigneeName = in.readString();
        this.ProdType = in.readString();
        this.loanAmnt = in.readString();
    }

    public static final Parcelable.Creator<LeadDetailsEntity> CREATOR = new Parcelable.Creator<LeadDetailsEntity>() {
        @Override
        public LeadDetailsEntity createFromParcel(Parcel source) {
            return new LeadDetailsEntity(source);
        }

        @Override
        public LeadDetailsEntity[] newArray(int size) {
            return new LeadDetailsEntity[size];
        }
    };
}