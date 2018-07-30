package com.rupeeboss.rba.core.controller.leadcapture;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.model.LeadRequest;

/**
 * Created by IN-RB on 02-02-2017.
 */
public interface ILeadCapture {
    public void insertLead(LeadRequest leadRequest, IResponseSubcriber iResponseSubcriber);
}
