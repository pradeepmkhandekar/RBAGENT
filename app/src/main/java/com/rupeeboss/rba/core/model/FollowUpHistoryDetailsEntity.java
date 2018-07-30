package com.rupeeboss.rba.core.model;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpHistoryDetailsEntity {

    /**
     * Date : 2016-07-06
     * Name : Vandana Karande
     * Remark : 10 lakhs amount in that 6,65,000/- BT and 3,31,000/- amount disbursed
     * Status : Login
     * time :
     */

    private String Date;
    private String Name;
    private String Remark;
    private String Status;
    private String time;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
