package com.rupeeboss.rba.core_loan_fm.controller.mainloan;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberFM;

import com.rupeeboss.rba.core_loan_fm.requestentity.BankSaveRequest;

/**
 * Created by IN-RB on 31-01-2018.
 */

public interface IMainLoan {

    void getHLQuoteApplicationData(int count, int QA, String fbaid, String type, IResponseSubcriberFM iResponseSubcriber);

  //  void saveHLQuoteData(FmHomeLoanRequest fmHomeLoanRequest, IResponseSubcriberFM iResponseSubcriber);

  //  void savePLQuoteData(FmPersonalLoanRequest fmPersonalLoanRequest, IResponseSubcriberFM iResponseSubcriber);

    void getPLQuoteApplication(int count, int type, String fbaid, IResponseSubcriberFM iResponseSubcriber);

    void savebankFbABuyData(BankSaveRequest bankSaveRequest, IResponseSubcriberFM iResponseSubcriber);

   // void saveBLQuoteData(FmBalanceLoanRequest fmBalanceLoanRequest, IResponseSubcriberFM iResponseSubcriber);

    void getBLQuoteApplication(int count, int type, String fbaid, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_loanrequest(String loan_requestID, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_personalrequest(String loan_requestID, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_balancerequest(String BalanceTransferId, IResponseSubcriberFM iResponseSubcriber);

    void getLoanApplication(int count, String type, String fbaid, IResponseSubcriberFM iResponseSubcriber);


    void getCityState(String PinCode, IResponseSubcriber iResponseSubcriber);
}
