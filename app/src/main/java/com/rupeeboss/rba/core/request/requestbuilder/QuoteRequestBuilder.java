package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;
import com.rupeeboss.rba.core.response.QuoteSelectedResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Nilesh Birhade on 02-02-2017.
 */

public class QuoteRequestBuilder extends RetroRequestBuilder {

    public QuoteNetworkService getService() {
        return super.build().create(QuoteNetworkService.class);
    }

    public interface QuoteNetworkService {

        @POST("/api/get-broker-quote")
        Call<QuoteDisplayResponse> getDisplayQuotes(@Body HashMap<String, String> bodyParameter);


        @POST("/api/quote-selected")
        Call<QuoteSelectedResponse> sendSelectedQuoteToServer(@Body HashMap<String, String> bodyParameter);
    }
}
