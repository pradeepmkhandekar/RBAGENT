package com.rupeeboss.rba.core.controller.BreakDetails;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public interface IBreakDetails {
    void sendBreakDetails(String empCode, String breakId, String time, IResponseSubcriber iResponseSubcribe);
}
