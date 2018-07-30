package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.DialerEntity;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class DialerResponse extends APIResponse {

    /**
     * result : {"custName":"Gaurav chauvan6","demoGiven":0,"empCode":"RB40000401","leadId":170050,"loanAmnt":0,"mobNo":"18008976580","prodName":""}
     */

    private DialerEntity result;

    public DialerEntity getResult() {
        return result;
    }


    public void setResult(DialerEntity result) {
        this.result = result;
    }


}
