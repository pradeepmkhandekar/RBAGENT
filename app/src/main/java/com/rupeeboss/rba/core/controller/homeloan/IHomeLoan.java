package com.rupeeboss.rba.core.controller.homeloan;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public interface IHomeLoan {

    void getHomeLoan(HomeLoanRequest homeLoanRequest, IResponseSubcriber iResponseSubcriber);
}
