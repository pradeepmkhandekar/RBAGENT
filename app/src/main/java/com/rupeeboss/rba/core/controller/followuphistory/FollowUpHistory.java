package com.rupeeboss.rba.core.controller.followuphistory;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.FollowUpHistoryDetailsRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpHistoryDetailsResponse;

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
    public void getFollowUpHistoryDetails(int LeadId, final IResponseSubcriber iResponseSubcribe) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("LeadId",""+LeadId);
        followUpHistoryDetailsNetworkService.getFollowUpHistoryDetails(bodyParameter).enqueue(new Callback<FollowUpHistoryDetailsResponse>() {
            @Override
            public void onResponse(Response<FollowUpHistoryDetailsResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcribe != null) {
                        if (response.body().getStatusId() == 0) {
                            try {
                                iResponseSubcribe.OnSuccess(response.body(), response.body().getMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcribe.OnFailure(new RuntimeException(response.body().getStatus()));
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
                }else {
                    iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }
}
