package com.rupeeboss.rba.core.request.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 10-02-2017.
 */

public class PersonalLoanRequest implements Parcelable {
    /**
     * LoanTenure : 5
     * LoanRequired : 500000
     * ApplicantNme : pratik
     * ApplicantSource : 1
     * ApplicantIncome : 70000
     * ApplicantObligations : 20000
     * ApplicantDOB : 1985-12-12
     * BrokerId : 33
     * empcode : rb40000412
     */

    private String LoanTenure;
    private String LoanRequired;
    private String ApplicantNme;
    private String ApplicantSource;
    private String ApplicantIncome;
    private String ApplicantObligations;
    private String ApplicantDOB;
    private String ApplicantGender;
    private String BrokerId;
    private String empcode;

    public PersonalLoanRequest() {

        this.LoanTenure = "";
        this.LoanRequired = "";
        this.ApplicantNme = "";
        this.ApplicantSource = "";
        this.ApplicantIncome = "";
        this.ApplicantObligations = "";
        this.ApplicantDOB = "";
        this.ApplicantGender = "";
        this.BrokerId="";
        this.empcode="";
    }

    public String getApplicantGender() {
        return ApplicantGender;
    }

    public void setApplicantGender(String applicantGender) {
        ApplicantGender = applicantGender;
    }

    public String getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(String LoanTenure) {
        this.LoanTenure = LoanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String LoanRequired) {
        this.LoanRequired = LoanRequired;
    }

    public String getApplicantNme() {
        return ApplicantNme;
    }

    public void setApplicantNme(String ApplicantNme) {
        this.ApplicantNme = ApplicantNme;
    }

    public String getApplicantSource() {
        return ApplicantSource;
    }

    public void setApplicantSource(String ApplicantSource) {
        this.ApplicantSource = ApplicantSource;
    }

    public String getApplicantIncome() {
        return ApplicantIncome;
    }

    public void setApplicantIncome(String ApplicantIncome) {
        this.ApplicantIncome = ApplicantIncome;
    }

    public String getApplicantObligations() {
        return ApplicantObligations;
    }

    public void setApplicantObligations(String ApplicantObligations) {
        this.ApplicantObligations = ApplicantObligations;
    }

    public String getApplicantDOB() {
        return ApplicantDOB;
    }

    public void setApplicantDOB(String ApplicantDOB) {
        this.ApplicantDOB = ApplicantDOB;
    }

    public String getBrokerId() {
        return BrokerId;
    }

    public void setBrokerId(String BrokerId) {
        this.BrokerId = BrokerId;
    }

    public String getempcode() {
        return empcode;
    }

    public void setempcode(String empcode) {
        this.empcode = empcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LoanTenure);
        dest.writeString(this.LoanRequired);
        dest.writeString(this.ApplicantNme);
        dest.writeString(this.ApplicantSource);
        dest.writeString(this.ApplicantIncome);
        dest.writeString(this.ApplicantObligations);
        dest.writeString(this.ApplicantDOB);
        dest.writeString(this.ApplicantGender);
        dest.writeString(this.BrokerId);
        dest.writeString(this.empcode);
    }

    protected PersonalLoanRequest(Parcel in) {
        this.LoanTenure = in.readString();
        this.LoanRequired = in.readString();
        this.ApplicantNme = in.readString();
        this.ApplicantSource = in.readString();
        this.ApplicantIncome = in.readString();
        this.ApplicantObligations = in.readString();
        this.ApplicantDOB = in.readString();
        this.ApplicantGender = in.readString();
        this.BrokerId = in.readString();
        this.empcode = in.readString();
    }

    public static final Parcelable.Creator<PersonalLoanRequest> CREATOR = new Parcelable.Creator<PersonalLoanRequest>() {
        @Override
        public PersonalLoanRequest createFromParcel(Parcel source) {
            return new PersonalLoanRequest(source);
        }

        @Override
        public PersonalLoanRequest[] newArray(int size) {
            return new PersonalLoanRequest[size];
        }
    };
}
