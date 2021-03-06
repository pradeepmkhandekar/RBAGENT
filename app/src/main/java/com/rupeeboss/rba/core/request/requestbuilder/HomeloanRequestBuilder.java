package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.GetQuoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class HomeloanRequestBuilder extends RetroRequestBuilder {

    public HomeloanNetworkService getService() {
        return super.build().create(HomeloanNetworkService.class);
    }

    public interface HomeloanNetworkService {

        @POST("/api/mobile-api-compare")
        Call<GetQuoteResponse> getQuotes(@Body HomeLoanRequest homeLoanRequest);


    }
}
