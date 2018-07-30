package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.FeedbackResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class FeedBackRequestBuilder extends WCFRetroRequestBuilder {

    public FeedBackNetworkService getService() {
        return super.build().create(FeedBackRequestBuilder.FeedBackNetworkService.class);
    }

    public interface FeedBackNetworkService {


        @POST("/LoginDtls.svc/XMLService/updtFeedBackDtls")
        Call<FeedbackResponse> sendFeedback(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/dsplyLeadDatadetailsForEmp")
        Call<FeedbackResponse> getFeedback(@Body HashMap<String, String> bodyParameter);

    }
}
