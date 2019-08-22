package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public  class BuisnessEnity implements Parcelable  {

    protected BuisnessEnity(Parcel in) {
        custName = in.readString();
        loanAmount = in.readDouble();
        product = in.readString();
        bank = in.readString();
    }

    public static final Creator<BuisnessEnity> CREATOR = new Creator<BuisnessEnity>() {
        @Override
        public BuisnessEnity createFromParcel(Parcel in) {
            return new BuisnessEnity(in);
        }

        @Override
        public BuisnessEnity[] newArray(int size) {
            return new BuisnessEnity[size];
        }
    };

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    /**
             * custName : sumit lead
             * loanAmount : 9000000
             * product : USED CAR Loan
             */

            private String custName;
            private double loanAmount;
            private String product;


    private String bank;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(custName);
        dest.writeDouble(loanAmount);
        dest.writeString(product);
        dest.writeString(bank);
    }
}