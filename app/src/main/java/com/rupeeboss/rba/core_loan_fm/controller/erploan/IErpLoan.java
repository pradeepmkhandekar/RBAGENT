package com.rupeeboss.rba.core_loan_fm.controller.erploan;


import com.rupeeboss.rba.core_loan_fm.IResponseSubcriber;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberERP;
import com.rupeeboss.rba.core_loan_fm.requestentity.ErpHomeLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.ErpPersonLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.HomeLoanApplyRequestEntity;

/**
 * Created by IN-RB on 04-03-2018.
 */

public interface IErpLoan {

    void getHomeLoanApplication(String ApplnId, IResponseSubcriberERP iResponseSubcriber);

    void saveERPHomeLoan(ErpHomeLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void saveERPLoanAgainstProperty(ErpHomeLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void getPersonalLoanApplication(String ApplnId, IResponseSubcriberERP iResponseSubcriber);

    void saveERPPersonalLoan(ErpPersonLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void getShareData(final IResponseSubcriber iResponseSubcriber);

    void getLeadDetails(String LeadID, IResponseSubcriberERP iResponseSubcriber);

    void generateLead(HomeLoanApplyRequestEntity homeLoanApplyRequestEntity, IResponseSubcriberERP iResponseSubcriber);

    void getcityloan(IResponseSubcriberERP iResponseSubcriber);

    void getCitywiseBankListloan(String cityid, String Productid, IResponseSubcriberERP iResponseSubcriber);
}
