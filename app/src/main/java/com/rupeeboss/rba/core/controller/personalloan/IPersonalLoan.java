package com.rupeeboss.rba.core.controller.personalloan;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;

/**
 * Created by IN-RB on 10-02-2017.
 */

public interface IPersonalLoan {
    void getPersonalLoan(PersonalLoanRequest personalLoanRequest, IResponseSubcriber iResponseSubcriber);

    void getPersonalQuote(int ProductId, String BrokerID, IResponseSubcriber iResponseSubcriber);
}
