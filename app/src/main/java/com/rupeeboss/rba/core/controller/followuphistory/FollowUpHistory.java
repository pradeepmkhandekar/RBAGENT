package com.rupeeboss.rba.core.controller.followuphistory;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.FollowUpHistoryDetailsRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpHistoryDetailsResponse;
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

public class FollowUpHistory implements IFollowUpHistory {
    FollowUpHistoryDetailsRequestBuilder.FollowUpHistoryDetailsNetworkService followUpHistoryDetailsNetworkService;
    public static FollowUpHistory followUpHistory;

    public FollowUpHistory() {
        followUpHistoryDetailsNetworkService = new FollowUpHistoryDetailsRequestBuilder().getService();
    }

    public FollowUpHistory getObject() {
        if (followUpHistory == null) {
            followUpHistory = new FollowUpHistory();
        }
        return followUpHistory;
    }


    @Override
    public void getFollowUpHistoryDetails(int LeadId, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("LeadId",""+LeadId);

        followUpHistoryDetailsNetworkService.getFollowUpHistoryDetails(bodyParameter).enqueue(new Callback<FollowUpHistoryDetailsResponse>() {
            @Override
            public void onResponse(Call<FollowUpHistoryDetailsResponse> call, Response<FollowUpHistoryDetailsResponse> response) {

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
            public void onFailure(Call<FollowUpHistoryDetailsResponse> call, Throwable t) {

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
