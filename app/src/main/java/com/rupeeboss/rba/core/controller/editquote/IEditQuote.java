package com.rupeeboss.rba.core.controller.editquote;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 02/03/2017.
 */

public interface IEditQuote {
    void getCustomerDetails(String ID, IResponseSubcriber iResponseSubcriber);
}
