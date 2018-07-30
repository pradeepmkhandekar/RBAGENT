package com.rupeeboss.rba.core.controller.application;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public interface IApplication {

    void getApplication(String BrokerID, IResponseSubcriber iResponseSubcriber);
}
