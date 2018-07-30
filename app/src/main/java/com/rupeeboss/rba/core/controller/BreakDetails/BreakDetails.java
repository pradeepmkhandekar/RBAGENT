package com.rupeeboss.rba.core.controller.BreakDetails;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.BreakDetailsRequestBuilder;
import com.rupeeboss.rba.core.response.BreakDetailsResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class BreakDetails implements IBreakDetails {


    BreakDetailsRequestBuilder.BreakDetailsNetworkService breakDetailsNetworkService;

    public BreakDetails() {
        breakDetailsNetworkService = new BreakDetailsRequestBuilder().getService();
    }

    @Override
    public void sendBreakDetails(String empCode, String breakId, String time, final IResponseSubcriber iResponseSubcribe) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("empCode", empCode);
        bodyParameter.put("breakId", breakId);
        bodyParameter.put("time", time);
        breakDetailsNetworkService.sendFeedback(bodyParameter).enqueue(new Callback<BreakDetailsResponse>() {
            @Override
            public void onResponse(Response<BreakDetailsResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcribe != null) {
                        if (response.body().getStatusId() == 0) {
                            try {
                                iResponseSubcribe.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcribe.OnFailure(new RuntimeException(response.body().getMessage()));
                        }
                    }
                } else {
                    iResponseSubcribe.OnFailure(new RuntimeException("Server down,Try after sometime..."));
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcribe.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                }else {
                    iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }
}
