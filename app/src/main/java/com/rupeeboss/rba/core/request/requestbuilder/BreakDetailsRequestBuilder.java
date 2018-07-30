package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.BreakDetailsResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class BreakDetailsRequestBuilder extends WCFRetroRequestBuilder {
    public BreakDetailsNetworkService getService() {

        return super.build().create(BreakDetailsRequestBuilder.BreakDetailsNetworkService.class);
    }

    public interface BreakDetailsNetworkService {


        @POST("/LoginDtls.svc/XMLService/insBreakDtls")
        Call<BreakDetailsResponse> sendFeedback(@Body HashMap<String, String> bodyParameter);

    }
}
