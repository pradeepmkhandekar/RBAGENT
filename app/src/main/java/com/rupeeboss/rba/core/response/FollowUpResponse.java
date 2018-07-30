package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.ListFollowUpEntity;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpResponse extends APIResponse {


    private ListFollowUpEntity result;

    public ListFollowUpEntity getResult() {
        return result;
    }

    public void setResult(ListFollowUpEntity result) {
        this.result = result;
    }


}
