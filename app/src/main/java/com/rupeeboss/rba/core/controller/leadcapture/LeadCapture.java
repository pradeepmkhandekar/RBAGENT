package com.rupeeboss.rba.core.controller.leadcapture;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.model.LeadRequest;
import com.rupeeboss.rba.core.request.requestbuilder.LeadCaptureRequestBuilder;
import com.rupeeboss.rba.core.response.LeadResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class LeadCapture implements ILeadCapture {

    LeadCaptureRequestBuilder.LeadCaptureNetworkService leadCaptureNetworkService;

    public LeadCapture() {

        leadCaptureNetworkService = new LeadCaptureRequestBuilder().getService();
    }
    @Override
    public void insertLead(LeadRequest leadRequest, final IResponseSubcriber iResponseSubcriber) {

        leadCaptureNetworkService.insertLead(leadRequest).enqueue(new Callback<LeadResponse>() {
            @Override
            public void onResponse(retrofit.Response<LeadResponse> response, Retrofit retrofit) {
                try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
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
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
