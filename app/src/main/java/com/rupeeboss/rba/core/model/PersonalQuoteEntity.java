package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by IN-RB on 07-02-2017.
 */
public class PersonalQuoteEntity implements Parcelable {
    /**
     * Bank_Id : 20
     * Bank_Code : HDFC
     * Bank_Name : HDFC BANK LTD
     * Product_Id : 9.00
     * roi : 11.69
     * loan_eligible : 905464.40
     * processingfee : 10000
     * emi : 20142
     * LoanTenure : 5
     * LoanRequired : 500000
     * Bank_Logo : http://erp.rupeeboss.com/Banklogo/hdfc.png
     * guarantor_required : No
     * Women_roi : null
     * Lock_In_Period : 12.00
     * Balance_Transfer : Yes
     * Top_up : Yes
     * eApproval : Yes
     * Pre_Closer_Fixed : 4.00
     * Part_Pmt_Fixed : null
     * Profession : 1
     */

    private int Bank_Id;
    private String Bank_Code;
    private String Bank_Name;
    private String Product_Id;
    private String roi;
    private String loan_eligible;
    private double processingfee;
    private double emi;
    private int LoanTenure;
    private String LoanRequired;
    private String Bank_Logo;
    private String guarantor_required;
    private String Women_roi;
    private String Lock_In_Period;
    private String Balance_Transfer;
    private String Top_up;
    private String eApproval;
    private String Pre_Closer_Fixed;
    private String Part_Pmt_Fixed;
    private int Profession;
    private String roi_type;


    public String getRoi_type() {
        return roi_type;
    }

    public void setRoi_type(String roi_type) {
        this.roi_type = roi_type;
    }

    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int Bank_Id) {
        this.Bank_Id = Bank_Id;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String Bank_Code) {
        this.Bank_Code = Bank_Code;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String Bank_Name) {
        this.Bank_Name = Bank_Name;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String Product_Id) {
        this.Product_Id = Product_Id;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getLoan_eligible() {
        return loan_eligible;
    }

    public void setLoan_eligible(String loan_eligible) {
        this.loan_eligible = loan_eligible;
    }

    public double getProcessingfee() {
        return processingfee;
    }

    public void setProcessingfee(double processingfee) {
        this.processingfee = processingfee;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public int getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(int LoanTenure) {
        this.LoanTenure = LoanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String LoanRequired) {
        this.LoanRequired = LoanRequired;
    }

    public String getBank_Logo() {
        return Bank_Logo;
    }

    public void setBank_Logo(String Bank_Logo) {
        this.Bank_Logo = Bank_Logo;
    }

    public String getGuarantor_required() {
        return guarantor_required;
    }

    public void setGuarantor_required(String guarantor_required) {
        this.guarantor_required = guarantor_required;
    }

    public String getWomen_roi() {
        return Women_roi;
    }

    public void setWomen_roi(String Women_roi) {
        this.Women_roi = Women_roi;
    }

    public String getLock_In_Period() {
        return Lock_In_Period;
    }

    public void setLock_In_Period(String Lock_In_Period) {
        this.Lock_In_Period = Lock_In_Period;
    }

    public String getBalance_Transfer() {
        return Balance_Transfer;
    }

    public void setBalance_Transfer(String Balance_Transfer) {
        this.Balance_Transfer = Balance_Transfer;
    }

    public String getTop_up() {
        return Top_up;
    }

    public void setTop_up(String Top_up) {
        this.Top_up = Top_up;
    }

    public String getEApproval() {
        return eApproval;
    }

    public void setEApproval(String eApproval) {
        this.eApproval = eApproval;
    }

    public String getPre_Closer_Fixed() {
        return Pre_Closer_Fixed;
    }

    public void setPre_Closer_Fixed(String Pre_Closer_Fixed) {
        this.Pre_Closer_Fixed = Pre_Closer_Fixed;
    }

    public String getPart_Pmt_Fixed() {
        return Part_Pmt_Fixed;
    }

    public void setPart_Pmt_Fixed(String Part_Pmt_Fixed) {
        this.Part_Pmt_Fixed = Part_Pmt_Fixed;
    }

    public int getProfession() {
        return Profession;
    }

    public void setProfession(int Profession) {
        this.Profession = Profession;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Bank_Id);
        dest.writeString(this.Bank_Code);
        dest.writeString(this.Bank_Name);
        dest.writeString(this.Product_Id);
        dest.writeString(this.roi);
        dest.writeString(this.loan_eligible);
        dest.writeDouble(this.processingfee);
        dest.writeDouble(this.emi);
        dest.writeInt(this.LoanTenure);
        dest.writeString(this.LoanRequired);
        dest.writeString(this.Bank_Logo);
        dest.writeString(this.guarantor_required);
        dest.writeString(this.Women_roi);
        dest.writeString(this.Lock_In_Period);
        dest.writeString(this.Balance_Transfer);
        dest.writeString(this.Top_up);
        dest.writeString(this.eApproval);
        dest.writeString(this.Pre_Closer_Fixed);
        dest.writeString(this.Part_Pmt_Fixed);
        dest.writeInt(this.Profession);
        dest.writeString(this.roi_type);
    }

    public PersonalQuoteEntity() {
    }

    protected PersonalQuoteEntity(Parcel in) {
        this.Bank_Id = in.readInt();
        this.Bank_Code = in.readString();
        this.Bank_Name = in.readString();
        this.Product_Id = in.readString();
        this.roi = in.readString();
        this.loan_eligible = in.readString();
        this.processingfee = in.readDouble();
        this.emi = in.readDouble();
        this.LoanTenure = in.readInt();
        this.LoanRequired = in.readString();
        this.Bank_Logo = in.readString();
        this.guarantor_required = in.readString();
        this.Women_roi = in.readString();
        this.Lock_In_Period = in.readString();
        this.Balance_Transfer = in.readString();
        this.Top_up = in.readString();
        this.eApproval = in.readString();
        this.Pre_Closer_Fixed = in.readString();
        this.Part_Pmt_Fixed = in.readString();
        this.Profession = in.readInt();
        this.roi_type = in.readString();
    }

    public static final Parcelable.Creator<PersonalQuoteEntity> CREATOR = new Parcelable.Creator<PersonalQuoteEntity>() {
        @Override
        public PersonalQuoteEntity createFromParcel(Parcel source) {
            return new PersonalQuoteEntity(source);
        }

        @Override
        public PersonalQuoteEntity[] newArray(int size) {
            return new PersonalQuoteEntity[size];
        }
    };
}
