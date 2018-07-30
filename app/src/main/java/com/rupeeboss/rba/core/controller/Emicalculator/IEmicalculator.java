package com.rupeeboss.rba.core.controller.Emicalculator;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.BuisnessLoanCalRequest;
import com.rupeeboss.rba.core.request.requestentity.WorkCapitalEmiRequestEntity;

/**
 * Created by IN-RB on 07-06-2017.
 */

public interface IEmicalculator {
    void getEmicalculatordata(String loanamount, String rateofint, String loantensure, IResponseSubcriber iResponseSubcriber);

    void getWorkingCapital(WorkCapitalEmiRequestEntity workCapitalEmiRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getBuisnessLoan(BuisnessLoanCalRequest buisnessLoanCalRequest, IResponseSubcriber iResponseSubcriber);

    void getBLQuotes(BuisnessLoanCalRequest buisnessLoanCalRequest, IResponseSubcriber iResponseSubcriber);
}
