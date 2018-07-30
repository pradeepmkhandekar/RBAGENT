package com.rupeeboss.rba.core.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.QuoteDisplayEntity;
import com.rupeeboss.rba.core.model.QuoteEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 02-02-2017.
 */

public class QuoteDisplayResponse extends APIResponse implements Parcelable {


    private List<QuoteDisplayEntity> data;
    private List<ApplicationDisplayEntity> application;

    public List<QuoteDisplayEntity> getQuoteList() {
        return data;
    }

    public void setData(List<QuoteDisplayEntity> data) {
        this.data = data;
    }

    public List<ApplicationDisplayEntity> getApplicationList() {
        return application;
    }

    public void setApplication(List<ApplicationDisplayEntity> application) {
        this.application = application;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeTypedList(this.application);


    }

    public QuoteDisplayResponse() {
    }

    protected QuoteDisplayResponse(Parcel in) {

        this.data = in.createTypedArrayList(QuoteDisplayEntity.CREATOR);
        this.application = in.createTypedArrayList(ApplicationDisplayEntity.CREATOR);
    }

    public static final Parcelable.Creator<QuoteDisplayResponse> CREATOR = new Parcelable.Creator<QuoteDisplayResponse>() {
        @Override
        public QuoteDisplayResponse createFromParcel(Parcel source) {
            return new QuoteDisplayResponse(source);
        }

        @Override
        public QuoteDisplayResponse[] newArray(int size) {
            return new QuoteDisplayResponse[size];
        }
    };
}
