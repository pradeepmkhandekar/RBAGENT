package com.rupeeboss.rba.core_loan_fm.controller.personalloan;


import com.rupeeboss.rba.core_loan_fm.IResponseSubcriber;
import com.rupeeboss.rba.core_loan_fm.requestentity.BLLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.equifax_personalloan_request;

/**
 * Created by IN-RB on 15-01-2018.
 */

public interface IPersonalLoan {

    void getPersonalLoan(PersonalLoanRequest personalLoanRequest, IResponseSubcriber iResponseSubcriber);

    void getBLQuote(BLLoanRequest blLoanRequest, IResponseSubcriber iResponseSubcriber);

    void getPLequifax(equifax_personalloan_request equifax_personalloan_requestlist, IResponseSubcriber iResponseSubcriber);
}
