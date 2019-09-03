package com.rupeeboss.rba.core_loan_fm.controller.homeloan;

import com.rupeeboss.rba.core_loan_fm.IResponseSubcriber;
import com.rupeeboss.rba.core_loan_fm.requestentity.HomeLoanRequest;


/**
 * Created by IN-RB on 12-01-2018.
 */

public interface IHomeLoan {

    void getHomeLoan(HomeLoanRequest homeLoanRequest, IResponseSubcriber iResponseSubcriber);

    void getRBCustomerData(String QuoteID, IResponseSubcriber iResponseSubcriber);


}
