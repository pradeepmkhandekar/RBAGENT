package com.rupeeboss.rba.core.request.requestbuilder;



import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.response.FestivalCampaignResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;


public class DynamicRequestBuilder extends RetroRequestBuilder {

    public DynamicRequestBuilder.DynamicRequestService getService() {
        return super.build().create(DynamicRequestBuilder.DynamicRequestService.class);
    }

    public interface DynamicRequestService {


        @POST()
        Call<FestivalCampaignResponse> getSalesMaterial(@Url String url, @Body HashMap<String, String> bodyParameter);

    }
}
