package com.rupeeboss.rba.core.controller.dialer;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 02-02-2017.
 */
public interface IDialer {

    void getLeadData(String empID, Context context, IResponseSubcriber iResponseSubcriber);

    void validateMobile(String EmpCode,String mobileNumber,IResponseSubcriber iResponseSubcriber);
}
