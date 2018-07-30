package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.FeedBackResponseEntity;

/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public class FeedbackResponse extends APIResponse {

    /**
     * result : {"Assignee":"240","Date":"30-11-2016","Name":"Sameer Naik","Product":"7","Remark":"Done....","Status":"23","time":"0:21"}
     */

    private FeedBackResponseEntity result;

    public FeedBackResponseEntity getResult() {
        return result;
    }

    public void setResult(FeedBackResponseEntity result) {
        this.result = result;
    }


}
