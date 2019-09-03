package com.rupeeboss.rba.core.controller.smslead;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.SmsLeadRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SendSmsRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SmsLeadRequestEntity;
import com.rupeeboss.rba.core.response.MyLeadResponse;
import com.rupeeboss.rba.core.response.SendSmsMobileResponse;
import com.rupeeboss.rba.core.response.SmsLeadResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class SmsLead implements ISmsLead {


    SmsLeadRequestBuilder.SmsLeadNetworkService smsLeadNetworkService;

    public static SmsLead smsLead;


    public SmsLead() {

        smsLeadNetworkService = new SmsLeadRequestBuilder().getService();
    }

    @Override
    public void getLeadSms(SmsLeadRequestEntity smsLeadRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        smsLeadNetworkService.getShareMessage(smsLeadRequestEntity).enqueue(new Callback<SmsLeadResponse>() {
            @Override
            public void onResponse(Call<SmsLeadResponse> call, Response<SmsLeadResponse> response) {

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
            public void onFailure(Call<SmsLeadResponse> call, Throwable t) {

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
    public void sendSmsMobileNoList(SendSmsRequestEntity sendSmsRequestEntity, final IResponseSubcriber iResponseSubcriber) {


        smsLeadNetworkService.sendSmsMobile(sendSmsRequestEntity).enqueue(new Callback<SendSmsMobileResponse>() {
            @Override
            public void onResponse(Call<SendSmsMobileResponse> call, Response<SendSmsMobileResponse> response) {

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
            public void onFailure(Call<SendSmsMobileResponse> call, Throwable t) {

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
    public void getMyLead(MyLeadRequestEntity myLeadrequestEntity, final IResponseSubcriber iResponseSubcriber) {

        smsLeadNetworkService.getMyLead(myLeadrequestEntity).enqueue(new Callback<MyLeadResponse>() {
            @Override
            public void onResponse(Call<MyLeadResponse> call, Response<MyLeadResponse> response) {

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
            public void onFailure(Call<MyLeadResponse> call, Throwable t) {

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
