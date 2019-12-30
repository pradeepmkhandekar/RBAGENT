package com.rupeeboss.rba.core_loan_fm.controller.masters;

import com.rupeeboss.rba.core_loan_fm.IResponseSubcriber;

import org.json.JSONObject;

/**
 * Created by Rajeev Ranjan on 13/02/2018.
 */

public interface IMaster {
    void getCityLoan(IResponseSubcriber iResponseSubcriber);
    void getquicklead(JSONObject params, IResponseSubcriber iResponseSubcriber);
}
