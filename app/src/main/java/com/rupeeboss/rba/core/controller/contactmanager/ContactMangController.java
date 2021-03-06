package com.rupeeboss.rba.core.controller.contactmanager;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.ContactToMangerRequestBuilder;
import com.rupeeboss.rba.core.response.ContactToMangerResponse;
import com.rupeeboss.rba.core.response.SendMailRespose;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IN-RB on 22-06-2017.
 */

public class ContactMangController implements IContactMang {

    Context mContext;
    ContactToMangerRequestBuilder.ContactToManagerNetworkService contactToManagerNetworkService;


    public ContactMangController(Context mContext) {
        this.mContext = mContext;
        contactToManagerNetworkService = new ContactToMangerRequestBuilder().getService();

    }

    @Override
    public void getEmpSupervisorDtls(String empCd, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("empCode", empCd);

        contactToManagerNetworkService.getEmpSupervisorDtls(bodyparameter).enqueue(new Callback<ContactToMangerResponse>() {
            @Override
            public void onResponse(Call<ContactToMangerResponse> call, Response<ContactToMangerResponse> response) {
                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ContactToMangerResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });


    }

    @Override
    public void sendMail(String strTo, String strmsgBody, String strsubject, String strcompanyId, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("to", strTo);
        bodyparameter.put("msgBody", strmsgBody);
        bodyparameter.put("subject", strsubject);
        bodyparameter.put("companyId", strcompanyId);

        contactToManagerNetworkService.sendMail(bodyparameter).enqueue(new Callback<SendMailRespose>() {
            @Override
            public void onResponse(Call<SendMailRespose> call, Response<SendMailRespose> response) {
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
            public void onFailure(Call<SendMailRespose> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });




    }
}
