package com.rupeeboss.rba.core.controller.dialer;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.DialerRequestBuilder;

import java.util.HashMap;


/**
 * Created by IN-RB on 02-02-2017.
 */
public class Dialer implements IDialer {

    DialerRequestBuilder.DialerNetworkService dialerNetworkService;
    public static Dialer dialer;

    public Dialer() {
        dialerNetworkService = new DialerRequestBuilder().getService();
    }

    public Dialer getObject() {
        if (dialer == null) {
            dialer = new Dialer();
        }

        return dialer;
    }

    @Override
    public void getLeadData(String empID, Context context, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("code", empID);
        bodyparameter.put("brokerId", "" + new LoginFacade(context).getUser().getBrokerId());

         //       dialerNetworkService.getLeadData(bodyparameter).enqueue(new Callback<DialerResponse>() {
//            @Override
//            public void onResponse(retrofit.Response<DialerResponse> response, Retrofit retrofit) {
//                try {
//                    if (response.body().getStatus_Id() == 0) {
//                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
//                    } else {
//                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
//                    }
//
//                } catch (InterruptedException e) {
//                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                if (t instanceof ConnectException) {
//                    iResponseSubcriber.OnFailure(t);
//                } else if (t instanceof SocketTimeoutException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else if (t instanceof UnknownHostException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else {
//                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
//                }
//            }
//        });
    }

    @Override
    public void validateMobile(String EmpCode, String mobileNumber, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("EmpCode", EmpCode);
        bodyparameter.put("MobileNumber", mobileNumber);

     //        dialerNetworkService.validateMobile(bodyparameter).enqueue(new Callback<ValidateMobileResponse>() {
//            @Override
//            public void onResponse(retrofit.Response<ValidateMobileResponse> response, Retrofit retrofit) {
//                try {
//                    if (response.body().getStatus_Id() == 0) {
//                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
//                    } else {
//                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
//                    }
//
//                } catch (InterruptedException e) {
//                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                if (t instanceof ConnectException) {
//                    iResponseSubcriber.OnFailure(t);
//                } else if (t instanceof SocketTimeoutException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else if (t instanceof UnknownHostException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else {
//                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
//                }
//            }
//        });
    }
}
