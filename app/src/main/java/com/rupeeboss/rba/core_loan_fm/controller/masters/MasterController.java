package com.rupeeboss.rba.core_loan_fm.controller.masters;

import android.content.Context;

import com.rupeeboss.rba.core_loan_fm.IResponseSubcriber;
import com.rupeeboss.rba.core_loan_fm.requestbuilder.MasterRequestBuilder;
import com.rupeeboss.rba.core_loan_fm.response.CityResponse;
import com.rupeeboss.rba.core_loan_fm.response.quickresponse;


import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 13/02/2018.
 */

public class MasterController implements IMaster {
    Context mContext;
    MasterRequestBuilder.MasterloanNetworkService masterloanNetworkService;

    public MasterController(Context mContext) {
        masterloanNetworkService = new MasterRequestBuilder().getService();
        this.mContext = mContext;
    }

    @Override
    public void getCityLoan(final IResponseSubcriber iResponseSubcriber) {
        masterloanNetworkService.getCity().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccess(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void getquicklead(JSONObject obj,final IResponseSubcriber iResponseSubcriberx) {
        String url = "http://bankapi.rupeeboss.com/BankAPIService.svc/createOtherLoanLeadReq";

        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", "");


        masterloanNetworkService.getNCD(url,body).enqueue(new Callback<quickresponse>() {
            @Override
            public void onResponse(Call<quickresponse> call, Response<quickresponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriberx.OnSuccess(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriberx.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<quickresponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriberx.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriberx.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriberx.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriberx.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriberx.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
