package com.rupeeboss.rba.core_loan_fm.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.rupeeboss.rba.core_loan_fm.requestentity.FmBalanceLoanRequest;

import java.util.List;

/**
 * Created by IN-RB on 23-02-2018.
 */

public class BLNodeMainEntity implements Parcelable {
    private List<FmBalanceLoanRequest> quote;
    private List<FmBalanceLoanRequest> application;

    protected BLNodeMainEntity(Parcel in) {
        this.quote = in.createTypedArrayList(FmBalanceLoanRequest.CREATOR);
        this.application = in.createTypedArrayList(FmBalanceLoanRequest.CREATOR);
    }

    public static final Creator<BLNodeMainEntity> CREATOR = new Creator<BLNodeMainEntity>() {
        @Override
        public BLNodeMainEntity createFromParcel(Parcel source) {
            return new BLNodeMainEntity(source);
        }

        @Override
        public BLNodeMainEntity[] newArray(int size) {
            return new BLNodeMainEntity[size];
        }
    };

    public List<FmBalanceLoanRequest> getQuote() {
        return quote;
    }

    public void setQuote(List<FmBalanceLoanRequest> quote) {
        this.quote = quote;
    }

    public List<FmBalanceLoanRequest> getApplication() {
        return application;
    }

    public void setApplication(List<FmBalanceLoanRequest> application) {
        this.application = application;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.quote);
        dest.writeTypedList(this.application);
    }


}
