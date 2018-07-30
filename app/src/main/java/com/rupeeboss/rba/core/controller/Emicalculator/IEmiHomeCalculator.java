package com.rupeeboss.rba.core.controller.Emicalculator;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.HomeEmiCalRequest;


/**
 * Created by IN-RB on 15-06-2017.
 */

public interface IEmiHomeCalculator {
    void getEmiHomecalculatordata(HomeEmiCalRequest homeLoanRequest, IResponseSubcriber iResponseSubcriber);


}
