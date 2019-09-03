package com.rupeeboss.rba.core.controller.mylist;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.MyListRequestBuilder;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.core.response.MyListResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 23/08/2017.
 */

public class MyListController implements IMyList {

    MyListRequestBuilder.MyListNetworkService myListNetworkService;
    Context mContext;

    public MyListController(Context context) {
        myListNetworkService = new MyListRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getParentList(String empCode, int brokerId, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();


        bodyParameter.put("empCode", empCode);
        bodyParameter.put("brokerId", "" + brokerId);

        myListNetworkService.getParentList(bodyParameter).enqueue(new Callback<MyListResponse>() {
            @Override
            public void onResponse(Call<MyListResponse> call, Response<MyListResponse> response) {

                try {
                    if (response.body().getStatusId()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<MyListResponse> call, Throwable t) {

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
