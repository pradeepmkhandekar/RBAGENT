package com.rupeeboss.rba.core.controller.BreakDetails;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.BreakDetailsRequestBuilder;
import com.rupeeboss.rba.core.response.BreakDetailsResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class BreakDetails implements IBreakDetails {


    BreakDetailsRequestBuilder.BreakDetailsNetworkService breakDetailsNetworkService;

    public BreakDetails() {
        breakDetailsNetworkService = new BreakDetailsRequestBuilder().getService();
    }

    @Override
    public void sendBreakDetails(String empCode, String breakId, String time, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("empCode", empCode);
        bodyParameter.put("breakId", breakId);
        bodyParameter.put("time", time);
        breakDetailsNetworkService.sendFeedback(bodyParameter).enqueue(new Callback<BreakDetailsResponse>() {

            @Override
            public void onResponse(Call<BreakDetailsResponse> call, Response<BreakDetailsResponse> response) {
                try {

                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                } catch (Exception e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<BreakDetailsResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }
}
