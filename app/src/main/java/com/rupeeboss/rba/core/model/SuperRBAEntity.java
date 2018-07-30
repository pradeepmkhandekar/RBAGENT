package com.rupeeboss.rba.core.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SuperRBAEntity extends ExpandableGroup<ChildRBAEntity> {
    /**
     * brokerId : 23302
     * brokerName : yash custom
     * childlst : [{"brokerId":23304,"brokerName":"test unknown","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23740,"brokerName":"vikas bb","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23807,"brokerName":"tdgdgd fsgs","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23815,"brokerName":"gsgst cscsf","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23824,"brokerName":"broker test","parentBrokerId":23302,"parentBrokerName":"yash custom"}]
     * parentBrokerId : 0
     * parentBrokerName :
     */

    private int brokerId;
    private String brokerName;
    private int parentBrokerId;
    private String parentBrokerName;
    private List<ChildRBAEntity> childlst;

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public int getParentBrokerId() {
        return parentBrokerId;
    }

    public void setParentBrokerId(int parentBrokerId) {
        this.parentBrokerId = parentBrokerId;
    }

    public String getParentBrokerName() {
        return parentBrokerName;
    }

    public void setParentBrokerName(String parentBrokerName) {
        this.parentBrokerName = parentBrokerName;
    }

    public List<ChildRBAEntity> getChildlst() {
        return childlst;
    }

    public void setChildlst(List<ChildRBAEntity> childlst) {
        this.childlst = childlst;
    }

    public SuperRBAEntity(String title, List<ChildRBAEntity> items) {
        super(title, items);
    }
}