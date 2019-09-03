package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core_loan_fm.APIResponseERP;

/**
 * Created by IN-RB on 04-03-2018.
 */

public class ERPSaveResponse extends APIResponseERP {


    /**
     * result : 0
     */

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
