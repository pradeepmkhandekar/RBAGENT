package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.Calendar;

/**
 * Created by Rajeev Ranjan on 06/01/2017.
 */

public class AudioEntity extends SugarRecord implements Parcelable {

    /**
     * file_name : video
     * user_id : 2000
     */
    public AudioEntity() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;
    private String file_name;
    private String user_id;
    private String convetedBase64;
    private boolean isUploaded;
    private long createdDate;
    private int lead_id;

    public int getLead_id() {
        return lead_id;
    }

    public void setLead_id(int lead_id) {
        this.lead_id = lead_id;
    }


    public String getConvetedBase64() {
        return convetedBase64;
    }

    public void setConvetedBase64(String convetedBase64) {
        this.convetedBase64 = convetedBase64;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate() {

        this.createdDate = Calendar.getInstance().getTimeInMillis();
    }


    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }


    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobile);
        dest.writeString(this.file_name);
        dest.writeString(this.user_id);
        dest.writeString(this.convetedBase64);
        dest.writeByte(this.isUploaded ? (byte) 1 : (byte) 0);
        dest.writeLong(this.createdDate);
        dest.writeInt(this.lead_id);
    }

    protected AudioEntity(Parcel in) {
        this.mobile = in.readString();
        this.file_name = in.readString();
        this.user_id = in.readString();
        this.convetedBase64 = in.readString();
        this.isUploaded = in.readByte() != 0;
        this.createdDate = in.readLong();
        this.lead_id = in.readInt();
    }

    public static final Parcelable.Creator<AudioEntity> CREATOR = new Parcelable.Creator<AudioEntity>() {
        @Override
        public AudioEntity createFromParcel(Parcel source) {
            return new AudioEntity(source);
        }

        @Override
        public AudioEntity[] newArray(int size) {
            return new AudioEntity[size];
        }
    };
}
