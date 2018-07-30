package com.rupeeboss.rba.core.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.BLQuoteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan on 19/09/2017.
 */

public class BLQuoteResponse extends APIResponse implements Parcelable {


    private List<BLQuoteEntity> data;

    public List<BLQuoteEntity> getData() {
        return data;
    }

    public void setData(List<BLQuoteEntity> data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public BLQuoteResponse() {
    }

    protected BLQuoteResponse(Parcel in) {
        this.data = new ArrayList<BLQuoteEntity>();
        in.readList(this.data, BLQuoteEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<BLQuoteResponse> CREATOR = new Parcelable.Creator<BLQuoteResponse>() {
        @Override
        public BLQuoteResponse createFromParcel(Parcel source) {
            return new BLQuoteResponse(source);
        }

        @Override
        public BLQuoteResponse[] newArray(int size) {
            return new BLQuoteResponse[size];
        }
    };
}
