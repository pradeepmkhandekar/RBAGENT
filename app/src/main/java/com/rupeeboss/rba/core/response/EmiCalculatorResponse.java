package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.EmiCalcuatorEntity;

/**
 * Created by IN-RB on 07-06-2017.
 */

public class EmiCalculatorResponse extends APIResponse {

    /**
     * status : 1
     * data : {"amount":103781,"total":18905,"ttl_payment":518905}
     * err :
     */

    private EmiCalcuatorEntity data;
    private String err;

    public EmiCalcuatorEntity getData() {
        return data;
    }

    public void setData(EmiCalcuatorEntity data) {
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }


}
