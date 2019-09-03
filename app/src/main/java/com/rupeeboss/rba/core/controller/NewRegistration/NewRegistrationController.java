package com.rupeeboss.rba.core.controller.NewRegistration;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.NewRegistrationRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.RegisterRequestEntity;
import com.rupeeboss.rba.core.response.FollowUpResponse;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.NewRegistrationResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.InputMismatchException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by IN-RB on 14-02-2017.
 */

public class NewRegistrationController  implements INewRegistration{

    Context mContext;
   NewRegistrationRequestBuilder.NewRegistrationNetworkService newRegistrationNetworkService;

    public static NewRegistrationController newRegistrationController;

    public NewRegistrationController(Context context)
    {
        mContext = context;
        newRegistrationNetworkService = new NewRegistrationRequestBuilder().getService();
    }

    @Override
    public void Register(RegisterRequestEntity entity, final IResponseSubcriber iResponseSubcriber) {


        newRegistrationNetworkService.saveNewRegestration(entity).enqueue(new Callback<NewRegistrationResponse>() {
            @Override
            public void onResponse(Call<NewRegistrationResponse> call, Response<NewRegistrationResponse> response) {

                try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<NewRegistrationResponse> call, Throwable t) {

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
