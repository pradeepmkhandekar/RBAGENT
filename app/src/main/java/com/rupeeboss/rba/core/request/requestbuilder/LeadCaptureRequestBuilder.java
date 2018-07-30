package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.model.LeadRequest;
import com.rupeeboss.rba.core.response.LeadResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class LeadCaptureRequestBuilder extends WCFRetroRequestBuilder {
    public LeadCaptureRequestBuilder.LeadCaptureNetworkService getService() {

        return super.build().create(LeadCaptureRequestBuilder.LeadCaptureNetworkService.class);
    }

    public interface LeadCaptureNetworkService {
        @POST("/LoginDtls.svc/XMLService/insLeadCaptures")
        Call<LeadResponse> insertLead(@Body LeadRequest bodyParameter);


    }
}
