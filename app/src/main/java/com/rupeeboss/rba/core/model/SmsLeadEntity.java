package com.rupeeboss.rba.core.model;

public class SmsLeadEntity {
    /**
     * smsflag : false
     */

    private boolean smsflag;

    public boolean isChk() {
        return chk;
    }

    public void setChk(boolean chk) {
        this.chk = chk;
    }

    /**
     * custName :  Asma  Afreen
     * leadId : 191111
     * mobNo : 9052217429
     */
    boolean chk;
    private String custName;
    private int leadId;
    private String mobNo;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public boolean isSmsflag() {
        return smsflag;
    }

    public void setSmsflag(boolean smsflag) {
        this.smsflag = smsflag;
    }
}