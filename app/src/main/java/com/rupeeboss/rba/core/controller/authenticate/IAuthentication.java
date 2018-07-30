package com.rupeeboss.rba.core.controller.authenticate;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 15-02-2017.
 */
public interface IAuthentication {

    void forgotPassword(String empMail,final IResponseSubcriber iResponseSubcribe);

    void changePassword(String empCode,String oldPassword,String newPassword,final IResponseSubcriber iResponseSubcribe);

    void getApiVersionCode(String appTypeId,final IResponseSubcriber iResponseSubcribe);
}
