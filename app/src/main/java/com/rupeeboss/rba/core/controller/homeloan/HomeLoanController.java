package com.rupeeboss.rba.core.controller.homeloan;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.HomeloanRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.GetQuoteResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
            public void onResponse(Response<GetQuoteResponse> response, Retrofit retrofit) {
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
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                }else{
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });
    }
}
