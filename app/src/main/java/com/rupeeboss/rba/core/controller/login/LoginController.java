package com.rupeeboss.rba.core.controller.login;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.homeloan.IHomeLoan;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.HomeloanRequestBuilder;
import com.rupeeboss.rba.core.request.requestbuilder.LoginRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.GetQuoteResponse;
import com.rupeeboss.rba.core.response.LoginResponse;
import com.rupeeboss.rba.core.response.ProfileResponse;
import com.rupeeboss.rba.login.LoginActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class LoginController implements ILogin {

    Context mContext;
    LoginRequestBuilder.LoginNetworkService loginNetworkService;

    public LoginController(Context context) {
        this.mContext = context;
        loginNetworkService = new LoginRequestBuilder().getService();
    }


    @Override
    public void login(String panNo, String password, String devID, String deviceToken, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("code", panNo);
        bodyparameter.put("pwd", password);
        bodyparameter.put("devId", devID);
        bodyparameter.put("tokenId", deviceToken);


        loginNetworkService.login(bodyparameter).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {

                        if (response.body().getStatusId() == 0) {
                            try {
                                //store user
                                new LoginFacade(mContext).storeUser(response.body().getResult());

                                // store profile pic
                                new LoginFacade(mContext).storeUserProfile(response.body().getResult().getProfilePic());

                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
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
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }
            }
        });
    }

    @Override
    public void uploadProfilePicture(String panNo, String base64Image, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("empCode", panNo);
        bodyparameter.put("profilePic", base64Image);



        loginNetworkService.uploadProfilePicture(bodyparameter).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Response<ProfileResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {

                        if (response.body().getStatus_Id() == 0) {
                            try {
                                // store profile pic
                                new LoginFacade(mContext).storeUserProfile(response.body().getProfilePic());

                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
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
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }
            }
        });
    }
}
