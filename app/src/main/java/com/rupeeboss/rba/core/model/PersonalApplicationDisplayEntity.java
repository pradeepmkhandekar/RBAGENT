package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 14-02-2017.
 */
public class PersonalApplicationDisplayEntity implements Parcelable{
    /**
     * AppId : 1533
     * ApplnStatus : Not Completed
     * ApplntName : Kedar
     * Gross_Income :
     * Loan_Cost :
     * Net_Income :
     * PAN_No :
     * ProdName : Home Loan
     * appLink : http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?appid=1533
     */

    private int AppId;
    private String ApplnStatus;
    private String ApplntName;
    private String Gross_Income;
    private String Loan_Cost;
    private String Net_Income;
    private String PAN_No;
    private String ProdName;
    private String appLink;

    public int getAppId() {
        return AppId;
    }

    public void setAppId(int AppId) {
        this.AppId = AppId;
    }

    public String getApplnStatus() {
        return ApplnStatus;
    }

    public void setApplnStatus(String ApplnStatus) {
        this.ApplnStatus = ApplnStatus;
    }

    public String getApplntName() {
        return ApplntName;
    }

    public void setApplntName(String ApplntName) {
        this.ApplntName = ApplntName;
    }

    public String getGross_Income() {
        return Gross_Income;
    }

    public void setGross_Income(String Gross_Income) {
        this.Gross_Income = Gross_Income;
    }

    public String getLoan_Cost() {
        return Loan_Cost;
    }

    public void setLoan_Cost(String Loan_Cost) {
        this.Loan_Cost = Loan_Cost;
    }

    public String getNet_Income() {
        return Net_Income;
    }

    public void setNet_Income(String Net_Income) {
        this.Net_Income = Net_Income;
    }

    public String getPAN_No() {
        return PAN_No;
    }

    public void setPAN_No(String PAN_No) {
        this.PAN_No = PAN_No;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String ProdName) {
        this.ProdName = ProdName;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(this.AppId);
        dest.writeString(this.ApplnStatus);
        dest.writeString(this.ApplntName);
        dest.writeString(this.Gross_Income);
        dest.writeString(this.Loan_Cost);
        dest.writeString(this.Net_Income);
        dest.writeString(this.PAN_No);
        dest.writeString(this.ProdName);
        dest.writeString(this.appLink);
    }

    protected PersonalApplicationDisplayEntity (Parcel in){
        this.AppId = in.readInt();
        this.ApplnStatus = in.readString();
        this.ApplntName = in.readString();
        this.Gross_Income = in.readString();
        this.Loan_Cost = in.readString();
        this.Net_Income = in.readString();
        this.PAN_No = in.readString();
        this.ProdName = in.readString();
        this.appLink = in.readString();
    }

   public static final  Creator<PersonalApplicationDisplayEntity> CREATOR = new Creator<PersonalApplicationDisplayEntity>() {
       @Override
       public PersonalApplicationDisplayEntity createFromParcel(Parcel source) {
           return new PersonalApplicationDisplayEntity(source);
       }

       @Override
       public PersonalApplicationDisplayEntity[] newArray(int size) {
           return new PersonalApplicationDisplayEntity[size];
       }
   };
}
