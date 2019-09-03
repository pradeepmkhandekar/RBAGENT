package com.rupeeboss.rba.core.controller.history;


import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.HistroyRequestBuilder;
import com.rupeeboss.rba.core.response.HistoryResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void getHistory(String mobileNumber, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("code", mobileNumber);

        histroyNetworkService.getCallingHistory(bodyparameter).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {

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
            public void onFailure(Call<HistoryResponse> call, Throwable t) {

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
