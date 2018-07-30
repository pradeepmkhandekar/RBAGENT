package com.rupeeboss.rba.core.request.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class HomeLoanRequest implements Parcelable {


    /**
     * PropertyID : 15
     * PropertyCost : 6000000
     * LoanTenure : 15
     * LoanRequired : 2900000
     * City : delhi
     * ApplicantNme : pratik
     * ApplicantGender : M
     * ApplicantSource : 2
     * ApplicantIncome : 100000
     * ApplicantObligations : 20000
     * ApplicantDOB : 1985-12-12
     * CoApplicantYes : Y
     * CoApplicantGender : F
     * CoApplicantSource : 1
     * CoApplicantIncome : 0
     * CoApplicantObligations : 0
     * CoApplicantDOB : 1993-12-12
     * Turnover : 1000000
     * ProfitAfterTax : 500000
     * Depreciation : 500000
     * DirectorRemuneration : 500000
     * CoApplicantTurnover : 100000
     * CoApplicantProfitAfterTax : 0
     * CoApplicantDepreciation : 0
     * CoApplicantDirectorRemuneration : 0
     * BrokerId : 33
     * ProductId : 12
     * empcode : rb40000412
     */

    private String PropertyID;
    private String PropertyCost;
    private String LoanTenure;
    private String LoanRequired;
    private String City;
    private String ApplicantNme;
    private String ApplicantGender;
    private String ApplicantSource;
    private String ApplicantIncome;
    private String ApplicantObligations;
    private String ApplicantDOB;
    private String CoApplicantYes;
    private String CoApplicantGender;
    private String CoApplicantSource;
    private String CoApplicantIncome;
    private String CoApplicantObligations;
    private String CoApplicantDOB;
    private String Turnover;
    private String ProfitAfterTax;
    private String Depreciation;
    private String DirectorRemuneration;
    private String CoApplicantTurnover;
    private String CoApplicantProfitAfterTax;
    private String CoApplicantDepreciation;
    private String CoApplicantDirectorRemuneration;
    private String BrokerId;
    private String ProductId;
    private String empcode;

    public HomeLoanRequest() {
        this.PropertyID="";
        this.PropertyCost="";
        this.LoanTenure="";
        this.LoanRequired="";
        this.City="";
        this.ApplicantNme="";
        this.ApplicantGender="";
        this.ApplicantSource="";
        this.ApplicantIncome="";
        this.ApplicantObligations="";
        this.ApplicantDOB="";
        this.CoApplicantYes="";
        this.CoApplicantGender="";
        this.CoApplicantSource="";
        this.CoApplicantIncome="";
        this.CoApplicantObligations="";
        this.CoApplicantDOB="";
        this.Turnover="";
        this.ProfitAfterTax="";
        this.Depreciation="";
        this.DirectorRemuneration="";
        this.CoApplicantTurnover="";
        this.CoApplicantProfitAfterTax="";
        this.CoApplicantDepreciation="";
        this.CoApplicantDirectorRemuneration="";
        this.BrokerId="";
        this.ProductId="";
        this.empcode="";
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String PropertyID) {
        this.PropertyID = PropertyID;
    }

    public String getPropertyCost() {
        return PropertyCost;
    }

    public void setPropertyCost(String PropertyCost) {
        this.PropertyCost = PropertyCost;
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

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getApplicantNme() {
        return ApplicantNme;
    }

    public void setApplicantNme(String ApplicantNme) {
        this.ApplicantNme = ApplicantNme;
    }

    public String getApplicantGender() {
        return ApplicantGender;
    }

    public void setApplicantGender(String ApplicantGender) {
        this.ApplicantGender = ApplicantGender;
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

    public String getCoApplicantYes() {
        return CoApplicantYes;
    }

    public void setCoApplicantYes(String CoApplicantYes) {
        this.CoApplicantYes = CoApplicantYes;
    }

    public String getCoApplicantGender() {
        return CoApplicantGender;
    }

    public void setCoApplicantGender(String CoApplicantGender) {
        this.CoApplicantGender = CoApplicantGender;
    }

    public String getCoApplicantSource() {
        return CoApplicantSource;
    }

    public void setCoApplicantSource(String CoApplicantSource) {
        this.CoApplicantSource = CoApplicantSource;
    }

    public String getCoApplicantIncome() {
        return CoApplicantIncome;
    }

    public void setCoApplicantIncome(String CoApplicantIncome) {
        this.CoApplicantIncome = CoApplicantIncome;
    }

    public String getCoApplicantObligations() {
        return CoApplicantObligations;
    }

    public void setCoApplicantObligations(String CoApplicantObligations) {
        this.CoApplicantObligations = CoApplicantObligations;
    }

    public String getCoApplicantDOB() {
        return CoApplicantDOB;
    }

    public void setCoApplicantDOB(String CoApplicantDOB) {
        this.CoApplicantDOB = CoApplicantDOB;
    }

    public String getTurnover() {
        return Turnover;
    }

    public void setTurnover(String Turnover) {
        this.Turnover = Turnover;
    }

    public String getProfitAfterTax() {
        return ProfitAfterTax;
    }

    public void setProfitAfterTax(String ProfitAfterTax) {
        this.ProfitAfterTax = ProfitAfterTax;
    }

    public String getDepreciation() {
        return Depreciation;
    }

    public void setDepreciation(String Depreciation) {
        this.Depreciation = Depreciation;
    }

    public String getDirectorRemuneration() {
        return DirectorRemuneration;
    }

    public void setDirectorRemuneration(String DirectorRemuneration) {
        this.DirectorRemuneration = DirectorRemuneration;
    }

    public String getCoApplicantTurnover() {
        return CoApplicantTurnover;
    }

    public void setCoApplicantTurnover(String CoApplicantTurnover) {
        this.CoApplicantTurnover = CoApplicantTurnover;
    }

    public String getCoApplicantProfitAfterTax() {
        return CoApplicantProfitAfterTax;
    }

    public void setCoApplicantProfitAfterTax(String CoApplicantProfitAfterTax) {
        this.CoApplicantProfitAfterTax = CoApplicantProfitAfterTax;
    }

    public String getCoApplicantDepreciation() {
        return CoApplicantDepreciation;
    }

    public void setCoApplicantDepreciation(String CoApplicantDepreciation) {
        this.CoApplicantDepreciation = CoApplicantDepreciation;
    }

    public String getCoApplicantDirectorRemuneration() {
        return CoApplicantDirectorRemuneration;
    }

    public void setCoApplicantDirectorRemuneration(String CoApplicantDirectorRemuneration) {
        this.CoApplicantDirectorRemuneration = CoApplicantDirectorRemuneration;
    }

    public String getBrokerId() {
        return BrokerId;
    }

    public void setBrokerId(String BrokerId) {
        this.BrokerId = BrokerId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
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
        dest.writeString(this.PropertyID);
        dest.writeString(this.PropertyCost);
        dest.writeString(this.LoanTenure);
        dest.writeString(this.LoanRequired);
        dest.writeString(this.City);
        dest.writeString(this.ApplicantNme);
        dest.writeString(this.ApplicantGender);
        dest.writeString(this.ApplicantSource);
        dest.writeString(this.ApplicantIncome);
        dest.writeString(this.ApplicantObligations);
        dest.writeString(this.ApplicantDOB);
        dest.writeString(this.CoApplicantYes);
        dest.writeString(this.CoApplicantGender);
        dest.writeString(this.CoApplicantSource);
        dest.writeString(this.CoApplicantIncome);
        dest.writeString(this.CoApplicantObligations);
        dest.writeString(this.CoApplicantDOB);
        dest.writeString(this.Turnover);
        dest.writeString(this.ProfitAfterTax);
        dest.writeString(this.Depreciation);
        dest.writeString(this.DirectorRemuneration);
        dest.writeString(this.CoApplicantTurnover);
        dest.writeString(this.CoApplicantProfitAfterTax);
        dest.writeString(this.CoApplicantDepreciation);
        dest.writeString(this.CoApplicantDirectorRemuneration);
        dest.writeString(this.BrokerId);
        dest.writeString(this.ProductId);
        dest.writeString(this.empcode);
    }

    protected HomeLoanRequest(Parcel in) {
        this.PropertyID = in.readString();
        this.PropertyCost = in.readString();
        this.LoanTenure = in.readString();
        this.LoanRequired = in.readString();
        this.City = in.readString();
        this.ApplicantNme = in.readString();
        this.ApplicantGender = in.readString();
        this.ApplicantSource = in.readString();
        this.ApplicantIncome = in.readString();
        this.ApplicantObligations = in.readString();
        this.ApplicantDOB = in.readString();
        this.CoApplicantYes = in.readString();
        this.CoApplicantGender = in.readString();
        this.CoApplicantSource = in.readString();
        this.CoApplicantIncome = in.readString();
        this.CoApplicantObligations = in.readString();
        this.CoApplicantDOB = in.readString();
        this.Turnover = in.readString();
        this.ProfitAfterTax = in.readString();
        this.Depreciation = in.readString();
        this.DirectorRemuneration = in.readString();
        this.CoApplicantTurnover = in.readString();
        this.CoApplicantProfitAfterTax = in.readString();
        this.CoApplicantDepreciation = in.readString();
        this.CoApplicantDirectorRemuneration = in.readString();
        this.BrokerId = in.readString();
        this.ProductId = in.readString();
        this.empcode = in.readString();
    }

    public static final Parcelable.Creator<HomeLoanRequest> CREATOR = new Parcelable.Creator<HomeLoanRequest>() {
        @Override
        public HomeLoanRequest createFromParcel(Parcel source) {
            return new HomeLoanRequest(source);
        }

        @Override
        public HomeLoanRequest[] newArray(int size) {
            return new HomeLoanRequest[size];
        }
    };
}
