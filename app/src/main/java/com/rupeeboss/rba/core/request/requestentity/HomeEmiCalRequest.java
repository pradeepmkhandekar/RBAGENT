package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by IN-RB on 23-06-2017.
 */

public class HomeEmiCalRequest {

    /**
     * PropertyCost : 200000
     * LoanTenure : 4
     * LoanRequired : 500000
     * ApplicantGender : M
     * ApplicantSource : 1
     * ApplicantIncome : 70000
     * ApplicantObligations : 200
     * Turnover : 0
     * ProfitAfterTax : 0
     * Depreciation : 0
     * DirectorRemuneration : 0
     * ProductId : 9
     */

    private String PropertyCost;
    private String LoanTenure;
    private String LoanRequired;
    private String ApplicantGender;
    private String ApplicantSource;
    private String ApplicantIncome;
    private String ApplicantObligations;
    private String Turnover;
    private String ProfitAfterTax;
    private String Depreciation;
    private String DirectorRemuneration;
    private String ProductId;

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

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public HomeEmiCalRequest() {

        this.PropertyCost="0";
        this.LoanTenure="";
        this.LoanRequired="0";

        this.ApplicantGender="";
        this.ApplicantSource="";
        this.ApplicantIncome="0";
        this.ApplicantObligations="0";

        this.Turnover="0";
        this.ProfitAfterTax="0";
        this.Depreciation="0";
        this.DirectorRemuneration="0";

        this.ProductId="";

    }
}
