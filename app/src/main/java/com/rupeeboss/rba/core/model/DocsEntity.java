package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DocsEntity implements Parcelable {


    private int image_path;


    public DocsEntity() {
    }
    public DocsEntity(int image_path) {
        this.image_path = image_path;
    }


    protected DocsEntity(Parcel in) {
        image_path = in.readInt();
    }

    public static final Creator<DocsEntity> CREATOR = new Creator<DocsEntity>() {
        @Override
        public DocsEntity createFromParcel(Parcel in) {
            return new DocsEntity(in);
        }

        @Override
        public DocsEntity[] newArray(int size) {
            return new DocsEntity[size];
        }
    };

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image_path);
    }
}