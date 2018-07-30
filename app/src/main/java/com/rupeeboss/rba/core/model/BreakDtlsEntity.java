package com.rupeeboss.rba.core.model;

public  class BreakDtlsEntity {
    /**
     * breakKey : 1
     * breakType : In Time
     * time : 60
     */

    private int breakKey;
    private String breakType;
    private int time;

    public int getBreakKey() {
        return breakKey;
    }

    public void setBreakKey(int breakKey) {
        this.breakKey = breakKey;
    }

    public String getBreakType() {
        return breakType;
    }

    public void setBreakType(String breakType) {
        this.breakType = breakType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}