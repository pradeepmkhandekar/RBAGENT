package com.rupeeboss.rba.core.controller.dynamic;



import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.DynamicRequestBuilder;
import com.rupeeboss.rba.core.response.FestivalCampaignResponse;
import com.rupeeboss.rba.core.response.GetQuoteResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by IN-RB on 14-11-2017.
 */

public class DynamicController implements IDynamic {

    DynamicRequestBuilder.DynamicRequestService dynamicRequestService;

    public DynamicController() {
        dynamicRequestService = new DynamicRequestBuilder().getService();
    }


    @Override
    public void getSalesMaterial(final IResponseSubcriber iResponseSubcriber) {


        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("brokerid", "0");


        dynamicRequestService.getSalesMaterial("http://beta.services.rupeeboss.com/LoginDtls.svc/XMLService/dsplySalesMaterial", bodyparameter).
                enqueue(new Callback<FestivalCampaignResponse>() {

                    @Override
                    public void onResponse(Call<FestivalCampaignResponse> call, Response<FestivalCampaignResponse> response) {

                        try {
                            if (response.body() != null) {

                                if (response.body().getStatusId() == 0) {
                                    iResponseSubcriber.OnSuccess(response.body(), response.message());
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
                    public void onFailure(Call<FestivalCampaignResponse> call, Throwable t) {

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
