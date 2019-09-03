package com.rupeeboss.rba.core.controller.editapplication;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EditApplicationRequestBuilder;
import com.rupeeboss.rba.core.response.EditApplicationResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 06/03/2017.
 */

public class EditApplicationController implements IEditApplication {

    Context mContext;
    EditApplicationRequestBuilder.EditApplicationNetworkService editApplicationNetworkService;

    public EditApplicationController(Context mContext) {
        this.mContext = mContext;
        editApplicationNetworkService = new EditApplicationRequestBuilder().getService();
    }

    @Override
    public void getDisplayApplication(int appId, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("appId", "" + appId);

        editApplicationNetworkService.getDisplayApplication(bodyparameter).enqueue(new Callback<EditApplicationResponse>() {
            @Override
            public void onResponse(Call<EditApplicationResponse> call, Response<EditApplicationResponse> response) {

                try {
                    if (response.body() != null) {


                        if (response.body().getStatus_Id() == 0) {
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
            public void onFailure(Call<EditApplicationResponse> call, Throwable t) {

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
