package com.rupeeboss.rba.core.request.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 05-07-2017.
 */

public class BuisnessLoanCalRequest implements Parcelable {

    private String applicant_dob;
    private String emp_detail;
    private  String turnover;

    private String profit_after_tax;
    private String depreciation;
    private  String partner_remuneration;

    private String interest_paid;
    private String emi;
    private  String no_of_emi_paid;

    private String loan_tenure;
    private String loan_amount;
    private  String date;

    private String Bank_Id;
    private String ProductId;

    public String getApplicant_dob() {
        return applicant_dob;
    }

    public void setApplicant_dob(String applicant_dob) {
        this.applicant_dob = applicant_dob;
    }

    public String getEmp_detail() {
        return emp_detail;
    }

    public void setEmp_detail(String emp_detail) {
        this.emp_detail = emp_detail;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getProfit_after_tax() {
        return profit_after_tax;
    }

    public void setProfit_after_tax(String profit_after_tax) {
        this.profit_after_tax = profit_after_tax;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(String depreciation) {
        this.depreciation = depreciation;
    }

    public String getPartner_remuneration() {
        return partner_remuneration;
    }

    public void setPartner_remuneration(String partner_remuneration) {
        this.partner_remuneration = partner_remuneration;
    }

    public String getInterest_paid() {
        return interest_paid;
    }

    public void setInterest_paid(String interest_paid) {
        this.interest_paid = interest_paid;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getNo_of_emi_paid() {
        return no_of_emi_paid;
    }

    public void setNo_of_emi_paid(String no_of_emi_paid) {
        this.no_of_emi_paid = no_of_emi_paid;
    }

    public String getLoan_tenure() {
        return loan_tenure;
    }

    public void setLoan_tenure(String loan_tenure) {
        this.loan_tenure = loan_tenure;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(String bank_Id) {
        Bank_Id = bank_Id;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.applicant_dob);
        dest.writeString(this.emp_detail);
        dest.writeString(this.turnover);
        dest.writeString(this.profit_after_tax);
        dest.writeString(this.depreciation);
        dest.writeString(this.partner_remuneration);
        dest.writeString(this.interest_paid);
        dest.writeString(this.emi);
        dest.writeString(this.no_of_emi_paid);
        dest.writeString(this.loan_tenure);
        dest.writeString(this.loan_amount);
        dest.writeString(this.date);
        dest.writeString(this.Bank_Id);
        dest.writeString(this.ProductId);
    }

    public BuisnessLoanCalRequest() {
    }

    protected BuisnessLoanCalRequest(Parcel in) {
        this.applicant_dob = in.readString();
        this.emp_detail = in.readString();
        this.turnover = in.readString();
        this.profit_after_tax = in.readString();
        this.depreciation = in.readString();
        this.partner_remuneration = in.readString();
        this.interest_paid = in.readString();
        this.emi = in.readString();
        this.no_of_emi_paid = in.readString();
        this.loan_tenure = in.readString();
        this.loan_amount = in.readString();
        this.date = in.readString();
        this.Bank_Id = in.readString();
        this.ProductId = in.readString();
    }

    public static final Parcelable.Creator<BuisnessLoanCalRequest> CREATOR = new Parcelable.Creator<BuisnessLoanCalRequest>() {
        @Override
        public BuisnessLoanCalRequest createFromParcel(Parcel source) {
            return new BuisnessLoanCalRequest(source);
        }

        @Override
        public BuisnessLoanCalRequest[] newArray(int size) {
            return new BuisnessLoanCalRequest[size];
        }
    };
}
