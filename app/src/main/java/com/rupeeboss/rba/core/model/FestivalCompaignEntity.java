package com.rupeeboss.rba.core.model;


import android.os.Parcel;
import android.os.Parcelable;

public class FestivalCompaignEntity  implements Parcelable {
    /**
     * campaignid : 1
     * name : test name
     * imagelink : http://api.magicfinmart.com/InsurerImages/car_44.png
     * title : test
     * description : test desc
     * shorturl : mgfm.in/1i1yaw
     * url : http://erp.rupeeboss.com?BrokerId=36886&FBAId=53686&client_source=Finmart&lead_id=
     * baseurl : http://erp.rupeeboss.com
     */

//    private int campaignid;
//    private String name;
//    private String imagelink;
//    private String title;
//    private String description;
//    private String shorturl;
//    private String url;
//    private String baseurl;
//
//    protected FestivalCompaignEntity(Parcel in) {
//        campaignid = in.readInt();
//        name = in.readString();
//        imagelink = in.readString();
//        title = in.readString();
//        description = in.readString();
//        shorturl = in.readString();
//        url = in.readString();
//        baseurl = in.readString();
//    }
//
//    public static final Parcelable.Creator<FestivalCompaignEntity> CREATOR = new Parcelable.Creator<FestivalCompaignEntity>() {
//        @Override
//        public FestivalCompaignEntity createFromParcel(Parcel in) {
//            return new FestivalCompaignEntity(in);
//        }
//
//        @Override
//        public FestivalCompaignEntity[] newArray(int size) {
//            return new FestivalCompaignEntity[size];
//        }
//    };
//
//    public int getCampaignid() {
//        return campaignid;
//    }
//
//    public void setCampaignid(int campaignid) {
//        this.campaignid = campaignid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getImagelink() {
//        return imagelink;
//    }
//
//    public void setImagelink(String imagelink) {
//        this.imagelink = imagelink;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getShorturl() {
//        return shorturl;
//    }
//
//    public void setShorturl(String shorturl) {
//        this.shorturl = shorturl;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getBaseurl() {
//        return baseurl;
//    }
//
//    public void setBaseurl(String baseurl) {
//        this.baseurl = baseurl;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(campaignid);
//        dest.writeString(name);
//        dest.writeString(imagelink);
//        dest.writeString(title);
//        dest.writeString(description);
//        dest.writeString(shorturl);
//        dest.writeString(url);
//        dest.writeString(baseurl);
//    }



    private String baseurl;
    private String description;
    private String Id;
    private String imagelink;
    private String Name;
    private String shorturl;
    private String title;
    private String url;

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        baseurl = baseurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.baseurl);
        dest.writeString(this.description);
        dest.writeString(this.Id);
        dest.writeString(this.imagelink);
        dest.writeString(this.Name);
        dest.writeString(this.shorturl);
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public FestivalCompaignEntity() {
    }

    protected FestivalCompaignEntity(Parcel in) {
        this.baseurl = in.readString();
        this.description = in.readString();
        this.Id = in.readString();
        this.imagelink = in.readString();
        this.Name = in.readString();
        this.shorturl = in.readString();
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<FestivalCompaignEntity> CREATOR = new Parcelable.Creator<FestivalCompaignEntity>() {
        @Override
        public FestivalCompaignEntity createFromParcel(Parcel source) {
            return new FestivalCompaignEntity(source);
        }

        @Override
        public FestivalCompaignEntity[] newArray(int size) {
            return new FestivalCompaignEntity[size];
        }
    };
}