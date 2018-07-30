package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.SuperRBAEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 23/08/2017.
 */

public class MyListResponse extends APIResponse {

    /**
     * result : {"lst":[{"brokerId":23302,"brokerName":"yash custom","childlst":[{"brokerId":23304,"brokerName":"test unknown","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23740,"brokerName":"vikas bb","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23807,"brokerName":"tdgdgd fsgs","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23815,"brokerName":"gsgst cscsf","parentBrokerId":23302,"parentBrokerName":"yash custom"},{"brokerId":23824,"brokerName":"broker test","parentBrokerId":23302,"parentBrokerName":"yash custom"}],"parentBrokerId":0,"parentBrokerName":""},{"brokerId":23558,"brokerName":"tdt ycy","childlst":null,"parentBrokerId":0,"parentBrokerName":null},{"brokerId":23743,"brokerName":"sms mobile","childlst":[{"brokerId":23991,"brokerName":"susmit test","parentBrokerId":23743,"parentBrokerName":"sms mobile"},{"brokerId":25410,"brokerName":"anit  sharma","parentBrokerId":23743,"parentBrokerName":"sms mobile"},{"brokerId":25411,"brokerName":"kapil patil","parentBrokerId":23743,"parentBrokerName":"sms mobile"}],"parentBrokerId":0,"parentBrokerName":null},{"brokerId":24103,"brokerName":"susmit share","childlst":null,"parentBrokerId":0,"parentBrokerName":null},{"brokerId":25208,"brokerName":"Erp added","childlst":null,"parentBrokerId":0,"parentBrokerName":null},{"brokerId":25209,"brokerName":"New erp","childlst":null,"parentBrokerId":0,"parentBrokerName":null},{"brokerId":25213,"brokerName":"new erp","childlst":null,"parentBrokerId":0,"parentBrokerName":null}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<SuperRBAEntity> lst;

        public List<SuperRBAEntity> getLst() {
            return lst;
        }

        public void setLst(List<SuperRBAEntity> lst) {
            this.lst = lst;
        }
    }
}
