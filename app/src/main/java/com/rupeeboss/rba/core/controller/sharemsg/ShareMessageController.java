package com.rupeeboss.rba.core.controller.sharemsg;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.ShareMessageRequestBuilder;
import com.rupeeboss.rba.core.response.ShareMessageResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
    public void getShareMessage(String empCode, String brokerid ,final IResponseSubcriber iResponseSubcribe) {

        HashMap<String, String> bodyParameters = new HashMap<String, String>();
        bodyParameters.put("EmpCode", empCode);
        bodyParameters.put("brokerid",brokerid);


        shareMessageNetworkService.getShareMessage(bodyParameters).enqueue(new Callback<ShareMessageResponse>() {
            @Override
            public void onResponse(Response<ShareMessageResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcribe != null) {
                        if (response.body().getStatus_Id() == 0) {
                            try {
                                iResponseSubcribe.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcribe.OnFailure(new RuntimeException(response.body().getMsg()));
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
