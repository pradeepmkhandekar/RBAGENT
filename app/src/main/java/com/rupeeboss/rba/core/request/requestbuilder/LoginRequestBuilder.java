package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.LoginResponse;
import com.rupeeboss.rba.core.response.ProfileResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class LoginRequestBuilder extends WCFRetroRequestBuilder {

    public LoginNetworkService getService() {
        return super.build().create(LoginNetworkService.class);
    }

    public interface LoginNetworkService {

        @POST("/LoginDtls.svc/XMLService/getUsrLoginDetailsForRBANew")
        Call<LoginResponse> login(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/upldEmpProfilePic")
        Call<ProfileResponse> uploadProfilePicture(@Body HashMap<String, String> bodyParameter);



    }
}
