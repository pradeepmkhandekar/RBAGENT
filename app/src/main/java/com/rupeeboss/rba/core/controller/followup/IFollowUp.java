package com.rupeeboss.rba.core.controller.followup;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 26/10/2016.
 */

public interface IFollowUp {
    void getFollowUp(String empID, IResponseSubcriber iResponseSubcribe);
}
