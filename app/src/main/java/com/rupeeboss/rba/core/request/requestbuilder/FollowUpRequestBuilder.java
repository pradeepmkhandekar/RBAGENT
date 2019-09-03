package com.rupeeboss.rba.core.request.requestbuilder;


import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Rajeev Ranjan on 26/10/2016.
 */

public class FollowUpRequestBuilder extends WCFRetroRequestBuilder {

    public FollowUpRequestBuilder.FollowUpNetworkService getService() {

        return super.build().create(FollowUpRequestBuilder.FollowUpNetworkService.class);
    }

    public interface FollowUpNetworkService {


        @POST("/LoginDtls.svc/XMLService/dsplyFollowupLeadDtlsRBA")
        Call<FollowUpResponse> getFollowUp(@Body HashMap<String, String> bodyParameter);

    }
}
