package com.rupeeboss.rba.core.request.requestbuilder;


import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.AppVersionResponse;
import com.rupeeboss.rba.core.response.ChangePasseordResponse;
import com.rupeeboss.rba.core.response.ForgotPasswordResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public class AuthenticationRequestBuilder extends WCFRetroRequestBuilder {

    public AuthenticationNetworkService getService() {
        return super.build().create(AuthenticationNetworkService.class);
    }

    public interface AuthenticationNetworkService {


        @POST("/LoginDtls.svc/XMLService/forgotpswdDtls")
        Call<ForgotPasswordResponse> sendForgotPassword(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/changePassword")
        Call<ChangePasseordResponse> sendChangePassword(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/dsplyAppVersionCode")
        Call<AppVersionResponse> getApiVersionCode(@Body HashMap<String, String> bodyParameter);

    }

}