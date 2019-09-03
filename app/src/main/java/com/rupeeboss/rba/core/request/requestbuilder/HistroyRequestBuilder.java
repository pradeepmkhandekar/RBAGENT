package com.rupeeboss.rba.core.request.requestbuilder;


import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.HistoryResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public class HistroyRequestBuilder extends
        WCFRetroRequestBuilder {

    public HistroyRequestBuilder.HistroyNetworkService getService() {

        return super.build().create(HistroyRequestBuilder.HistroyNetworkService.class);
    }

    public interface HistroyNetworkService {


        @POST("/LoginDtls.svc/XMLService/dsplyLeadDataHstry")
        Call<HistoryResponse> getCallingHistory(@Body HashMap<String, String> bodyParameter);

    }
}
