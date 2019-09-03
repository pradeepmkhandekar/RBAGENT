package com.rupeeboss.rba.core.controller.sharemsg;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.ShareMessageRequestBuilder;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.core.response.ShareMessageResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by IN-RB on 02-02-2017.
 */
public class ShareMessageController implements IShareMessage {


    ShareMessageRequestBuilder.ShareMessageNetworkService  shareMessageNetworkService;

    public static ShareMessageController shareMessageController;


    public ShareMessageController() {

        shareMessageNetworkService = new ShareMessageRequestBuilder().getService();
    }

    @Override
    public void getShareMessage(String empCode, String brokerid ,final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameters = new HashMap<String, String>();
        bodyParameters.put("EmpCode", empCode);
        bodyParameters.put("brokerid",brokerid);

        shareMessageNetworkService.getShareMessage(bodyParameters).enqueue(new Callback<ShareMessageResponse>() {
            @Override
            public void onResponse(Call<ShareMessageResponse> call, Response<ShareMessageResponse> response) {

                try {
                    if (response.body().getStatus_Id()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<ShareMessageResponse> call, Throwable t) {

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
