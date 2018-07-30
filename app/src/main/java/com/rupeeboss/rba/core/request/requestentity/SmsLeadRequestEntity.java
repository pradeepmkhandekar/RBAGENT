package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class SmsLeadRequestEntity {
    /**
     * code : rb40000401
     * type : HL
     * pgNo : 5
     */

    private String code;
    private String type;
    private int pgNo;
    /**
     * brokerId :
     */

    private String brokerId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
