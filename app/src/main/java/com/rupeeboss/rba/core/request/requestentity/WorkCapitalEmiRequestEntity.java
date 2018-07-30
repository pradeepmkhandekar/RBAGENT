package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by Rajeev Ranjan on 29/06/2017.
 */

public class WorkCapitalEmiRequestEntity {
    /**
     * turnover : 0
     * profitbefore : 0
     * depreciation : 0
     * financecost : 0
     * inventory : 0
     * debtors : 0
     * creditors : 0
     * existing : 0
     */

    private String turnover;
    private String profitbefore;
    private String depreciation;
    private String financecost;
    private String inventory;
    private String debtors;
    private String creditors;
    private String existing;

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getProfitbefore() {
        return profitbefore;
    }

    public void setProfitbefore(String profitbefore) {
        this.profitbefore = profitbefore;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(String depreciation) {
        this.depreciation = depreciation;
    }

    public String getFinancecost() {
        return financecost;
    }

    public void setFinancecost(String financecost) {
        this.financecost = financecost;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getDebtors() {
        return debtors;
    }

    public void setDebtors(String debtors) {
        this.debtors = debtors;
    }

    public String getCreditors() {
        return creditors;
    }

    public void setCreditors(String creditors) {
        this.creditors = creditors;
    }

    public String getExisting() {
        return existing;
    }

    public void setExisting(String existing) {
        this.existing = existing;
    }
}
