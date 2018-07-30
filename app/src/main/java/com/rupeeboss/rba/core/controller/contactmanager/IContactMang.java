package com.rupeeboss.rba.core.controller.contactmanager;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 22-06-2017.
 */

public interface IContactMang {

    void getEmpSupervisorDtls(String empCd, IResponseSubcriber iResponseSubcriber);

    void sendMail(String strTo,String strmsgBody,String strsubject,String strcompanyId, IResponseSubcriber iResponseSubcriber);
}
