package com.rupeeboss.rba.core.controller.login;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.LoginRequestBuilder;
import com.rupeeboss.rba.core.response.LoginResponse;
import com.rupeeboss.rba.core.response.ProfileResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void login(String panNo, String password, String devID, String deviceToken, final IResponseSubcriber IResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("code", panNo);
        bodyparameter.put("pwd", password);
        bodyparameter.put("devId", devID);
        bodyparameter.put("tokenId", deviceToken);

        loginNetworkService.login(bodyparameter).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                try {
                    if (response.body().getStatusId() == 0) {


                        //store user
                        new LoginFacade(mContext).storeUser(response.body().getResult());

                        // store profile pic
                        new LoginFacade(mContext).storeUserProfile(response.body().getResult().getProfilePic());

                        IResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());

                    } else {
                        IResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }

                } catch (InterruptedException e) {
                    IResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    IResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    IResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    IResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    IResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
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
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                try {
                    if (response.body().getStatus_Id()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                        new LoginFacade(mContext).storeUserProfile(response.body().getProfilePic());

                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

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
