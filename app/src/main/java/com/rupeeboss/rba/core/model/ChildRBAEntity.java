package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChildRBAEntity implements Parcelable {
    /**
     * brokerId : 23304
     * brokerName : test unknown
     * parentBrokerId : 23302
     * parentBrokerName : yash custom
     */

    private int brokerId;
    private String brokerName;
    private int parentBrokerId;
    private String parentBrokerName;

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public int getParentBrokerId() {
        return parentBrokerId;
    }

    public void setParentBrokerId(int parentBrokerId) {
        this.parentBrokerId = parentBrokerId;
    }

    public String getParentBrokerName() {
        return parentBrokerName;
    }

    public void setParentBrokerName(String parentBrokerName) {
        this.parentBrokerName = parentBrokerName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.brokerId);
        dest.writeString(this.brokerName);
        dest.writeInt(this.parentBrokerId);
        dest.writeString(this.parentBrokerName);
    }

    public ChildRBAEntity() {
    }

    protected ChildRBAEntity(Parcel in) {
        this.brokerId = in.readInt();
        this.brokerName = in.readString();
        this.parentBrokerId = in.readInt();
        this.parentBrokerName = in.readString();
    }

    public static final Parcelable.Creator<ChildRBAEntity> CREATOR = new Parcelable.Creator<ChildRBAEntity>() {
        @Override
        public ChildRBAEntity createFromParcel(Parcel source) {
            return new ChildRBAEntity(source);
        }

        @Override
        public ChildRBAEntity[] newArray(int size) {
            return new ChildRBAEntity[size];
        }
    };
}