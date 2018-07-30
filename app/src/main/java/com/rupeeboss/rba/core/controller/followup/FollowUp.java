package com.rupeeboss.rba.core.controller.followup;


import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.FollowUpRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpResponse;

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

public class FollowUp implements IFollowUp {

    Context context;
    FollowUpRequestBuilder.FollowUpNetworkService followUpNetworkService;
    public static FollowUp followUp;


    public FollowUp(Context context) {
        this.context = context;
        followUpNetworkService = new FollowUpRequestBuilder().getService();
    }


    @Override
    public void getFollowUp(String empID, final IResponseSubcriber iResponseSubcribe) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("code", empID);
        bodyParameter.put("brokerId", "" + new LoginFacade(context).getUser().getBrokerId());
        followUpNetworkService.getFollowUp(bodyParameter).enqueue(new Callback<FollowUpResponse>() {
            @Override
            public void onResponse(Response<FollowUpResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcribe != null) {
                        if (response.body().getStatusId() == 0) {
                            try {
                                iResponseSubcribe.OnSuccess(response.body(), response.body().getMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcribe.OnFailure(new RuntimeException(response.body().getMessage()));
                        }
                    }

                } else {
                    iResponseSubcribe.OnFailure(new RuntimeException("Server Down Please try after some time !"));
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
                } else {
                    iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }
}
