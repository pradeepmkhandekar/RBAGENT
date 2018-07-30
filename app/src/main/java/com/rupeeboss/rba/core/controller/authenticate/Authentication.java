package com.rupeeboss.rba.core.controller.authenticate;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.AuthenticationRequestBuilder;
import com.rupeeboss.rba.core.response.AppVersionResponse;
import com.rupeeboss.rba.core.response.ChangePasseordResponse;
import com.rupeeboss.rba.core.response.ForgotPasswordResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 15-02-2017.
 */
public class Authentication implements IAuthentication {

    Context mContext;
    AuthenticationRequestBuilder.AuthenticationNetworkService authenticationNetworkService;
    public static Authentication authentication;


    public Authentication(Context context) {
        this.mContext = context;
        authenticationNetworkService = new AuthenticationRequestBuilder().getService();
    }

    public Authentication getObject() {
        if (authentication == null) {
            authentication = new Authentication(mContext);
        }

        return authentication;
    }


    @Override
    public void forgotPassword(String empMail, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("code", empMail);

        authenticationNetworkService.sendForgotPassword(bodyparameter).enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Response<ForgotPasswordResponse> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body().getStatus_Id() == 0) {
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

    @Override
    public void changePassword(String empCode, String oldPassword, String newPassword, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyparameter = new HashMap<String, String>();

        bodyparameter.put("code", empCode);
        bodyparameter.put("oldPassword", oldPassword);
        bodyparameter.put("newPassword", newPassword);

        authenticationNetworkService.sendChangePassword(bodyparameter).enqueue(new Callback<ChangePasseordResponse>() {
            @Override
            public void onResponse(Response<ChangePasseordResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body().getStatusId() == 0)
                        {
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
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

    @Override
    public void getApiVersionCode(String appTypeId, final IResponseSubcriber iResponseSubcriber) {


        HashMap<String, String> bodyparameter = new HashMap<String, String>();

    bodyparameter.put("appTypeId", appTypeId);

        authenticationNetworkService.getApiVersionCode(bodyparameter).enqueue(new Callback<AppVersionResponse>() {
            @Override
            public void onResponse(Response<AppVersionResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body().getStatusId() == 0)
                        {
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
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
