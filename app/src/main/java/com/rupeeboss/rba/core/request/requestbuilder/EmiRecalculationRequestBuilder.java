package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.response.EmiRecalculationResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 06-07-2017.
 */

public class EmiRecalculationRequestBuilder  extends RetroRequestBuilder {

    public EmiRecalculationNetworkService getService() {
        return super.build().create(EmiRecalculationNetworkService.class);
    }

    public interface EmiRecalculationNetworkService {
        @POST("/api/revise-calculation")
        Call<EmiRecalculationResponse> getEmiRecalculationdata(@Body HashMap<String, String> bodyParameter);

    }
}
