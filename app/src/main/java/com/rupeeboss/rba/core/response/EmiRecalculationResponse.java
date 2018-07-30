package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.request.requestentity.EmiRecalculationEntity;

/**
 * Created by IN-RB on 05-07-2017.
 */

public class EmiRecalculationResponse  extends APIResponse {

    /**
     * success : 1
     * data : {"emi":11122.22,"after_savings":46364.47,"loaninterest":12,"drop_emi_new":772.74,"drop_in_int_new":3}
     * err :
     */

    private int success;
    private EmiRecalculationEntity data;
    private String err;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public EmiRecalculationEntity getData() {
        return data;
    }

    public void setData(EmiRecalculationEntity data) {
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }


}
