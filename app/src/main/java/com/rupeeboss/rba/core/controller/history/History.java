package com.rupeeboss.rba.core.controller.history;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.HistroyRequestBuilder;
import com.rupeeboss.rba.core.response.HistoryResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public class History implements IHistory {

    HistroyRequestBuilder.HistroyNetworkService histroyNetworkService;
    public static History history;


    public History() {

        histroyNetworkService = new HistroyRequestBuilder().getService();
    }

    public History getObject() {
        if (history == null) {
            history = new History();
        }
        return history;
    }


    @Override
    public void getHistory(String mobileNumber, final IResponseSubcriber iResponseSubcribe) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("code", mobileNumber);

        histroyNetworkService.getCallingHistory(bodyparameter).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Response<HistoryResponse> response, Retrofit retrofit) {
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
