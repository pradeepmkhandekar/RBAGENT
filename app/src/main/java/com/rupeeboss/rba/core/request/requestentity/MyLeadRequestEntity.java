package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by Rajeev Ranjan on 03/03/2017.
 */

public class MyLeadRequestEntity {
    /**
     * code :
     * pgNo : 1
     * brokerId : 6096
     */

    private String code;
    private int pgNo;
    private String brokerId;


    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPgNo() {
        return pgNo;
    }

    public void setPgNo(int pgNo) {
        this.pgNo = pgNo;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
