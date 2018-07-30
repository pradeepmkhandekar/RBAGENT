package com.rupeeboss.rba.core.controller.Emicalculator;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.HomeEmiCalRequest;

/**
 * Created by IN-RB on 29-06-2017.
 */

public interface IEmiPersonalCalculator {

    void getEmiPersonalcalculatordata(HomeEmiCalRequest homeLoanRequest, IResponseSubcriber iResponseSubcriber);


}
