package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 15-06-2017.
 */
public  class EmiHomeCalcuatorEntity implements Parcelable {
    /**
     * Bank_Id : 20
     * Bank_Code : HDFC
     * Bank_Name : HDFC BANK LTD
     * Product_Id : 9.00
     * roi : 11.49
     * loan_eligible : 500000
     * processingfee : 10000
     * emi : 13042
     * foir : 40.00
     * netincome : 69800
     * LoanTenure : 4
     * LoanRequired : 500000
     * Bank_Logo : http://erp.rupeeboss.com/Banklogo/hdfc.png
     * guarantor_required : No
     * Women_roi : 11.49
     * Lock_In_Period : 12.00
     * Balance_Transfer : Yes
     * Top_up : Yes
     * eApproval : Yes
     * Pre_Closer_Fixed : 4.000
     * Part_Pmt_Fixed : null
     * Profession : 1
     * roi_type : Fixed
     */

    private int Bank_Id;

    protected EmiHomeCalcuatorEntity(Parcel in) {
        Bank_Id = in.readInt();
        Bank_Code = in.readString();
        Bank_Name = in.readString();
        Product_Id = in.readString();
        roi = in.readString();
        loan_eligible = in.readString();
        processingfee = in.readDouble();
        emi = in.readDouble();
        foir = in.readString();
        netincome = in.readString();
        LoanTenure = in.readDouble();
        LoanRequired = in.readString();
        Bank_Logo = in.readString();
        guarantor_required = in.readString();
        Women_roi = in.readString();
        Lock_In_Period = in.readString();
        Balance_Transfer = in.readString();
        Top_up = in.readString();
        eApproval = in.readString();
        Pre_Closer_Fixed = in.readString();
        Part_Pmt_Fixed = in.readString();
        Profession = in.readDouble();
        roi_type = in.readString();
    }

    public static final Creator<EmiHomeCalcuatorEntity> CREATOR = new Creator<EmiHomeCalcuatorEntity>() {
        @Override
        public EmiHomeCalcuatorEntity createFromParcel(Parcel in) {
            return new EmiHomeCalcuatorEntity(in);
        }

        @Override
        public EmiHomeCalcuatorEntity[] newArray(int size) {
            return new EmiHomeCalcuatorEntity[size];
        }
    };

    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int bank_Id) {
        Bank_Id = bank_Id;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String bank_Code) {
        Bank_Code = bank_Code;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        Bank_Name = bank_Name;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String product_Id) {
        Product_Id = product_Id;
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

    public String getFoir() {
        return foir;
    }

    public void setFoir(String foir) {
        this.foir = foir;
    }

    public String getNetincome() {
        return netincome;
    }

    public void setNetincome(String netincome) {
        this.netincome = netincome;
    }

    public double getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(double loanTenure) {
        LoanTenure = loanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String loanRequired) {
        LoanRequired = loanRequired;
    }

    public String getBank_Logo() {
        return Bank_Logo;
    }

    public void setBank_Logo(String bank_Logo) {
        Bank_Logo = bank_Logo;
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

    public void setWomen_roi(String women_roi) {
        Women_roi = women_roi;
    }

    public String getLock_In_Period() {
        return Lock_In_Period;
    }

    public void setLock_In_Period(String lock_In_Period) {
        Lock_In_Period = lock_In_Period;
    }

    public String getBalance_Transfer() {
        return Balance_Transfer;
    }

    public void setBalance_Transfer(String balance_Transfer) {
        Balance_Transfer = balance_Transfer;
    }

    public String getTop_up() {
        return Top_up;
    }

    public void setTop_up(String top_up) {
        Top_up = top_up;
    }

    public String geteApproval() {
        return eApproval;
    }

    public void seteApproval(String eApproval) {
        this.eApproval = eApproval;
    }

    public String getPre_Closer_Fixed() {
        return Pre_Closer_Fixed;
    }

    public void setPre_Closer_Fixed(String pre_Closer_Fixed) {
        Pre_Closer_Fixed = pre_Closer_Fixed;
    }

    public String getPart_Pmt_Fixed() {
        return Part_Pmt_Fixed;
    }

    public void setPart_Pmt_Fixed(String part_Pmt_Fixed) {
        Part_Pmt_Fixed = part_Pmt_Fixed;
    }

    public double getProfession() {
        return Profession;
    }

    public void setProfession(double profession) {
        Profession = profession;
    }

    public String getRoi_type() {
        return roi_type;
    }

    public void setRoi_type(String roi_type) {
        this.roi_type = roi_type;
    }

    private String Bank_Code;
    private String Bank_Name;
    private String Product_Id;
    private String roi;
    private String loan_eligible;
    private double processingfee;
    private double emi;
    private String foir;
    private String netincome;
    private double LoanTenure;
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
    private double Profession;
    private String roi_type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Bank_Id);
        dest.writeString(Bank_Code);
        dest.writeString(Bank_Name);
        dest.writeString(Product_Id);
        dest.writeString(roi);
        dest.writeString(loan_eligible);
        dest.writeDouble(processingfee);
        dest.writeDouble(emi);
        dest.writeString(foir);
        dest.writeString(netincome);
        dest.writeDouble(LoanTenure);
        dest.writeString(LoanRequired);
        dest.writeString(Bank_Logo);
        dest.writeString(guarantor_required);
        dest.writeString(Women_roi);
        dest.writeString(Lock_In_Period);
        dest.writeString(Balance_Transfer);
        dest.writeString(Top_up);
        dest.writeString(eApproval);
        dest.writeString(Pre_Closer_Fixed);
        dest.writeString(Part_Pmt_Fixed);
        dest.writeDouble(Profession);
        dest.writeString(roi_type);
    }
}