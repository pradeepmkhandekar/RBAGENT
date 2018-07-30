package com.rupeeboss.rba.core.request.requestbuilder;


import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.model.LeadRequest;
import com.rupeeboss.rba.core.response.CityResponse;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 29-06-2017.
 */

public class BusinessRequestBuilder extends WCFRetroRequestBuilder {

    public BusinessRequestBuilder.BusinessNetworkService getService()
    {
        return super.build().create(BusinessRequestBuilder.BusinessNetworkService.class);
    }


    public  interface BusinessNetworkService
    {

        @POST("/LoginDtls.svc/XMLService/dsplyMyBusinessData")
        Call<MyBusinessResponse> myBuisness(@Body HashMap<String, String> bodyParameter);

    }
}
