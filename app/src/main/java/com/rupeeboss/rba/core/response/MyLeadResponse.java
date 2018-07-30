package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.LeadDetailsEntity;
import com.rupeeboss.rba.core.model.MyLeadResult;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 03/03/2017.
 */

public class MyLeadResponse extends APIResponse {
    /**
     * result : {"empCode":"","lstLeads":[{"custName":"Rajeev","leadId":253736,"mobNo":"87075407764","status":"Property Doc Pending"},{"custName":"sghh","leadId":253972,"mobNo":"958627555","status":"New"},{"custName":"sumit broker1","leadId":253123,"mobNo":"9874361452","status":"Property Doc Pending"},{"custName":"sumit2 broker","leadId":253126,"mobNo":"9780521365","status":"Different City"},{"custName":"sumit3 broker","leadId":253732,"mobNo":"9412548752","status":"Different City"}],"totalCount":4}
     */

    private MyLeadResult result;

    public MyLeadResult getResult() {
        return result;
    }

    public void setResult(MyLeadResult result) {
        this.result = result;
    }

}
