package com.rupeeboss.rba.core.controller.homeloan;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.HomeloanRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.GetQuoteResponse;
import com.rupeeboss.rba.core.response.LeadResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class HomeLoanController implements IHomeLoan {

    Context mContext;
    HomeloanRequestBuilder.HomeloanNetworkService homeloanNetworkService;

    public HomeLoanController(Context context) {
        this.mContext = context;
        homeloanNetworkService = new HomeloanRequestBuilder().getService();
    }

    @Override
    public void getHomeLoan(HomeLoanRequest homeLoanRequest, final IResponseSubcriber iResponseSubcriber) {

        homeloanNetworkService.getQuotes(homeLoanRequest).enqueue(new Callback<GetQuoteResponse>() {
            @Override
            public void onResponse(Call<GetQuoteResponse> call, Response<GetQuoteResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatus_Id() == 0) {
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
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
            public void onFailure(Call<GetQuoteResponse> call, Throwable t) {

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
