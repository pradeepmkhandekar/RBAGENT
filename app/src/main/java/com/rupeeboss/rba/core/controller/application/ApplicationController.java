package com.rupeeboss.rba.core.controller.application;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.quote.IDisplayQuote;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.ApplicantionRequestBuilder;
import com.rupeeboss.rba.core.request.requestbuilder.QuoteRequestBuilder;
import com.rupeeboss.rba.core.response.ApplicantResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplicationController implements IApplication {

    Context mContext;
    ApplicantionRequestBuilder.ApplicantionNetworkService applicantionNetworkService;

    public ApplicationController(Context context) {
        this.mContext = context;
        applicantionNetworkService = new ApplicantionRequestBuilder().getService();
    }


    @Override
    public void getApplication(String BrokerID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("BrokerId", BrokerID);
        bodyParameter.put("flag","RBAPP");
        bodyParameter.put("empCode",new LoginFacade(mContext).getUser().getEmpCode());

        applicantionNetworkService.getApplications(bodyParameter).enqueue(new Callback<ApplicantResponse>() {
            @Override
            public void onResponse(Call<ApplicantResponse> call, Response<ApplicantResponse> response) {

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
            public void onFailure(Call<ApplicantResponse> call, Throwable t) {

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
