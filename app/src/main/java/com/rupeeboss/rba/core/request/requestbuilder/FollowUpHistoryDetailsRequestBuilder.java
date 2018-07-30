package com.rupeeboss.rba.core.request.requestbuilder;


import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.FollowUpHistoryDetailsResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpHistoryDetailsRequestBuilder extends WCFRetroRequestBuilder {

    public FollowUpHistoryDetailsRequestBuilder.FollowUpHistoryDetailsNetworkService getService() {
        return super.build().create(FollowUpHistoryDetailsRequestBuilder.FollowUpHistoryDetailsNetworkService.class);
    }


    public interface FollowUpHistoryDetailsNetworkService {
        @POST("/LoginDtls.svc/XMLService/dsplyFollowupLeadHstryDtls")
        Call<FollowUpHistoryDetailsResponse> getFollowUpHistoryDetails(@Body HashMap<String, String> bodyParameter);
    }
}


