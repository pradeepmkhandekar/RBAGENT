package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.response.EditQuoteResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 02/03/2017.
 */

public class EditQuoteRequestBuilder extends RetroRequestBuilder {
    public EditQuoteNetworkService getService() {
        return super.build().create(EditQuoteNetworkService.class);
    }

    public interface EditQuoteNetworkService {

        @POST("/api/getcustomer")
        Call<EditQuoteResponse> getDisplayQuotes(@Body HashMap<String, String> bodyParameter);


    }
}
