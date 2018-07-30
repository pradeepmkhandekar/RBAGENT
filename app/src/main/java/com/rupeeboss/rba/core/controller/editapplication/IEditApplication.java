package com.rupeeboss.rba.core.controller.editapplication;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 06/03/2017.
 */

public interface IEditApplication {
    void getDisplayApplication(int appId, IResponseSubcriber iResponseSubcriber);
}
