package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public  class ResultDataMyBuisness implements Parcelable {

    private String totalCount;
    private  double totalLoanAmnt;
    private List<BuisnessEnity> lstRpt;


    protected ResultDataMyBuisness(Parcel in) {
        totalCount = in.readString();
        totalLoanAmnt = in.readDouble();
        lstRpt = in.createTypedArrayList(BuisnessEnity.CREATOR);
    }

    public static final Creator<ResultDataMyBuisness> CREATOR = new Creator<ResultDataMyBuisness>() {
        @Override
        public ResultDataMyBuisness createFromParcel(Parcel in) {
            return new ResultDataMyBuisness(in);
        }

        @Override
        public ResultDataMyBuisness[] newArray(int size) {
            return new ResultDataMyBuisness[size];
        }
    };

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalLoanAmnt() {
        return totalLoanAmnt;
    }

    public void setTotalLoanAmnt(double totalLoanAmnt) {
        this.totalLoanAmnt = totalLoanAmnt;
    }

    public List<BuisnessEnity> getLstRpt() {
        return lstRpt;
    }

    public void setLstRpt(List<BuisnessEnity> lstRpt) {
        this.lstRpt = lstRpt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(totalCount);
        dest.writeDouble(totalLoanAmnt);
        dest.writeTypedList(lstRpt);
    }

    /**
         * lstRpt : [{"custName":"sumit lead","loanAmount":9000000,"product":"USED CAR Loan"}]
         * totalCount : 1
         */




}