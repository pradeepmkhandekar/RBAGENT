package com.rupeeboss.rba.core.controller.mybuisness;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.BusinessRequestBuilder;
import com.rupeeboss.rba.core.response.LoginResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 29-06-2017.
 */

public class MyBusinessController implements  IMyBusinessController {

    Context mContext;

    BusinessRequestBuilder.BusinessNetworkService businessNetworkService ;

    public MyBusinessController(Context mContext) {
        this.mContext = mContext;
        businessNetworkService = new BusinessRequestBuilder().getService();
    }

    @Override
    public void myBuisness(String empCode, String type, String brokerId, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("empCode", empCode);
        bodyparameter.put("type", type);
        bodyparameter.put("brokerId", brokerId);
        bodyparameter.put("bank", "bank");

        businessNetworkService.myBuisness(bodyparameter).enqueue(new Callback<MyBusinessResponse>() {
            @Override
            public void onResponse(Response<MyBusinessResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {

                        if (response.body().getStatusId() == 0) {
                            try {

                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                        }
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.message()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Server down,Try after sometime..."));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }else  {
                    iResponseSubcriber.OnFailure(t);
                }
            }
        });
    }
}
