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

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
    public void getLeadSms(SmsLeadRequestEntity smsLeadRequestEntity, final IResponseSubcriber iResponseSubcribe) {
        smsLeadNetworkService.getShareMessage(smsLeadRequestEntity).enqueue(new Callback<SmsLeadResponse>() {
            @Override
            public void onResponse(Response<SmsLeadResponse> response, Retrofit retrofit) {
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
                if (iResponseSubcribe != null) {
                    if (t instanceof ConnectException) {
                        iResponseSubcribe.OnFailure(t);
                    } else if (t instanceof SocketTimeoutException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else if (t instanceof UnknownHostException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else {
                        iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                    }
                }
            }
        });
    }

    @Override
    public void sendSmsMobileNoList(SendSmsRequestEntity sendSmsRequestEntity, final IResponseSubcriber iResponseSubcribe) {
        smsLeadNetworkService.sendSmsMobile(sendSmsRequestEntity).enqueue(new Callback<SendSmsMobileResponse>() {
            @Override
            public void onResponse(Response<SendSmsMobileResponse> response, Retrofit retrofit) {
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
                if (iResponseSubcribe != null) {
                    if (t instanceof ConnectException) {
                        iResponseSubcribe.OnFailure(t);
                    } else if (t instanceof SocketTimeoutException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else if (t instanceof UnknownHostException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else {
                        iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                    }
                }
            }
        });
    }

    @Override
    public void getMyLead(MyLeadRequestEntity myLeadrequestEntity, final IResponseSubcriber iResponseSubcribe) {
        smsLeadNetworkService.getMyLead(myLeadrequestEntity).enqueue(new Callback<MyLeadResponse>() {
            @Override
            public void onResponse(Response<MyLeadResponse> response, Retrofit retrofit) {
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
                if (iResponseSubcribe != null) {
                    if (t instanceof ConnectException) {
                        iResponseSubcribe.OnFailure(t);
                    } else if (t instanceof SocketTimeoutException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else if (t instanceof UnknownHostException) {
                        iResponseSubcribe.OnFailure(new RuntimeException("Check your internet connection"));
                    } else {
                        iResponseSubcribe.OnFailure(new RuntimeException(t.getMessage()));
                    }
                }
            }
        });
    }
}
