package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.RegisterRequestEntity;
import com.rupeeboss.rba.core.response.FollowUpResponse;
import com.rupeeboss.rba.core.response.NewRegistrationResponse;


import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 14-02-2017.
 */

public class NewRegistrationRequestBuilder extends WCFRetroRequestBuilder{


    public NewRegistrationRequestBuilder.NewRegistrationNetworkService getService(){

        return  super.build().create(NewRegistrationRequestBuilder.NewRegistrationNetworkService.class);
    }

    public interface NewRegistrationNetworkService{

        @POST("/LoginDtls.svc/XMLService/insBrokerDataForRBA")
        Call<NewRegistrationResponse> saveNewRegestration(@Body RegisterRequestEntity bodyParameter);

    }

}
