package com.rupeeboss.rba.core.controller.followup;


import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.FollowUpRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

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

public class FollowUp implements IFollowUp {

    Context context;
    FollowUpRequestBuilder.FollowUpNetworkService followUpNetworkService;
    public static FollowUp followUp;


    public FollowUp(Context context) {
        this.context = context;
        followUpNetworkService = new FollowUpRequestBuilder().getService();
    }


    @Override
    public void getFollowUp(String empID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("code", empID);
        bodyParameter.put("brokerId", "" + new LoginFacade(context).getUser().getBrokerId());

        followUpNetworkService.getFollowUp(bodyParameter).enqueue(new Callback<FollowUpResponse>() {
            @Override
            public void onResponse(Call<FollowUpResponse> call, Response<FollowUpResponse> response) {

                try {
                    if (response.body() != null) {


                        if (response.body().getStatusId() == 0) {
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                        }
                    } else {
                        //failure
                        iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<FollowUpResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }


}
