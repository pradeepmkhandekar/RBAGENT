package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.BuisnessEnity;
import com.rupeeboss.rba.core.model.ResultDataMyBuisness;

import java.util.List;

/**
 * Created by IN-RB on 29-06-2017.
 */

public class MyBusinessResponse extends APIResponse {


    /**
     * result : {"lstRpt":[{"custName":"sumit lead","loanAmount":9000000,"product":"USED CAR Loan"}],"totalCount":"1"}
     */

    private ResultDataMyBuisness result;

    public ResultDataMyBuisness getResult() {
        return result;
    }

    public void setResult(ResultDataMyBuisness result) {
        this.result = result;
    }


}
