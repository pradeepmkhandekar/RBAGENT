package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.EditApplicationResponse;
import com.rupeeboss.rba.core.response.EditQuoteResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 06/03/2017.
 */

public class EditApplicationRequestBuilder extends WCFRetroRequestBuilder {
    public EditApplicationRequestBuilder.EditApplicationNetworkService getService() {
        return super.build().create(EditApplicationRequestBuilder.EditApplicationNetworkService.class);
    }

    public interface EditApplicationNetworkService {

        @POST("/LoginDtls.svc/XMLService/dsplyHomeLoanAppDtlsFromAppId")
        Call<EditApplicationResponse> getDisplayApplication(@Body HashMap<String, String> bodyParameter);


    }
}
