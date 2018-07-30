package com.rupeeboss.rba.core.model;

/**
 * Created by IN-RB on 02-02-2017.
 */

public class LstShareMessageEntity {

    private String MsgBody;
    private String link;
    private String title;
    /**
     * lnkType :
     */

    private String lnkType;

    public String getMsgBody() {
        return MsgBody;
    }

    public void setMsgBody(String MsgBody) {
        this.MsgBody = MsgBody;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLnkType() {
        return lnkType;
    }

    public void setLnkType(String lnkType) {
        this.lnkType = lnkType;
    }
}
