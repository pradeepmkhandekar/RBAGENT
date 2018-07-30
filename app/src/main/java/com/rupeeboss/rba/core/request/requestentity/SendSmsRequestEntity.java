package com.rupeeboss.rba.core.request.requestentity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class SendSmsRequestEntity {

    /**
     * lstleadMob : [{"mobNo":"9820535509","leadId":"16872"},{"mobNo":"8108877272","leadId":"23"}]
     * msg : Hello..this is test message
     */

    private String msg;
    private List<LeadMobEntity> lstleadMob;
    /**
     * type : HL
     */

    private String type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<LeadMobEntity> getLstleadMob() {
        return lstleadMob;
    }

    public void setLstleadMob(List<LeadMobEntity> lstleadMob) {
        this.lstleadMob = lstleadMob;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
