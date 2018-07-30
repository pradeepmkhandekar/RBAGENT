package com.rupeeboss.rba.core.controller.smslead;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SendSmsRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SmsLeadRequestEntity;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public interface ISmsLead {
    void getLeadSms(SmsLeadRequestEntity smsLeadRequestEntity, IResponseSubcriber iResponseSubcriber);

    void sendSmsMobileNoList(SendSmsRequestEntity sendSmsRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getMyLead(MyLeadRequestEntity myLeadrequestEntity, IResponseSubcriber iResponseSubcriber);

}
