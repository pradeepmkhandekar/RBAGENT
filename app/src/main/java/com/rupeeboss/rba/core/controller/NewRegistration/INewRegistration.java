package com.rupeeboss.rba.core.controller.NewRegistration;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.RegisterRequestEntity;

/**
 * Created by IN-RB on 14-02-2017.
 * {
 "first_Name":"Shubhangi",
 "last_Name":"G",
 "contact_No":"345678778",
 "Email_Id":"shubhangi.g@gmail.com",
 "UserPassword":"ryryrtyrty=005",
 "PAN_No":"ALFDF56756",
 "City":"677",
 "parentBrokerId":7
 }

 */

public interface INewRegistration {
  //  void savenewReg(String fname,String lname,String contactNo, String emailId, String userPwd, String panNo ,String city, String parentBrokerId, String parentEmpCode, IResponseSubcriber iResponseSubcriber);
    void  Register(RegisterRequestEntity entity, IResponseSubcriber iResponseSubcriber);
}
