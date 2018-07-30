package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by IN-RB on 06-07-2017.
 */

public  class EmiRecalculationEntity {
    /**
     * emi : 11122.22
     * after_savings : 46364.47
     * loaninterest : 12
     * drop_emi_new : 772.74
     * drop_in_int_new : 3
     */

    private double emi;
    private double after_savings;
    private double loaninterest;
    private double drop_emi_new;
    private double drop_in_int_new;

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public double getAfter_savings() {
        return after_savings;
    }

    public void setAfter_savings(double after_savings) {
        this.after_savings = after_savings;
    }

    public double getLoaninterest() {
        return loaninterest;
    }

    public void setLoaninterest(double loaninterest) {
        this.loaninterest = loaninterest;
    }

    public double getDrop_emi_new() {
        return drop_emi_new;
    }

    public void setDrop_emi_new(double drop_emi_new) {
        this.drop_emi_new = drop_emi_new;
    }

    public double getDrop_in_int_new() {
        return drop_in_int_new;
    }

    public void setDrop_in_int_new(double drop_in_int_new) {
        this.drop_in_int_new = drop_in_int_new;
    }
}