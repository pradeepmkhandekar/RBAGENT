package com.rupeeboss.rba.core.controller.Emicalculator;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 06-07-2017.
        */

public interface IEmiRecalculation {

    void getEmiRecalculationdata(String loanamount, String rateofint, String loantensure,String old_loaninterest, IResponseSubcriber iResponseSubcriber);
}
