package com.rupeeboss.rba.core.controller.editapplication;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EditApplicationRequestBuilder;
import com.rupeeboss.rba.core.response.EditApplicationResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
            public void onResponse(Response<EditApplicationResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body( ).getStatus_Id()== 0) {
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                        }
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.message()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Server down,Try after sometime..."));
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
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });
    }
}
