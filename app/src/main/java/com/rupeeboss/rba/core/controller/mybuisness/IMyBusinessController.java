package com.rupeeboss.rba.core.controller.mybuisness;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 29-06-2017.
 */

public interface IMyBusinessController {

    void myBuisness(String empCode, String type, String brokerId, IResponseSubcriber IResponseSubcriber);
}
