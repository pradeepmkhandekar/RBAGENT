package com.rupeeboss.rba.core.controller.followuphistory;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public interface IFollowUpHistory {
    public void getFollowUpHistoryDetails(int LeadId, IResponseSubcriber iResponseSubcribe);
}
