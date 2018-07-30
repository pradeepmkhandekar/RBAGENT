package com.rupeeboss.rba.core.controller.feedback;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.FeedBackRequestBuilder;
import com.rupeeboss.rba.core.response.FeedbackResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class FeedBack implements IFeedBack {

    FeedBackRequestBuilder.FeedBackNetworkService feedBackNetworkService;


    public FeedBack() {
        feedBackNetworkService = new FeedBackRequestBuilder().getService();
    }

    @Override
    public void sendFeedback(String empID, int leadId, String name, int status, String remark, int assignee, int product, String date, String time, int demoGiven, String ExpctDisbsDate, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("empcode", empID);
        bodyParameter.put("Name", name);
        bodyParameter.put("status", String.valueOf(status));
        bodyParameter.put("remark", remark);
        bodyParameter.put("assignee", String.valueOf(assignee));
        bodyParameter.put("product", String.valueOf(product));
        bodyParameter.put("date", date);
        bodyParameter.put("time", time);
        bodyParameter.put("leadId", "" + leadId);
        bodyParameter.put("demoGiven", "" + demoGiven);
        bodyParameter.put("ExpctDisbsDate", ExpctDisbsDate);

        feedBackNetworkService.sendFeedback(bodyParameter).enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Response<FeedbackResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body().getStatus_Id() == 0) {
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
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
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void getFeedback(String mobNo, IResponseSubcriber iResponseSubcriber) {

    }
}
