package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.response.CityResponse;
import com.rupeeboss.rba.core.response.ProductResponse;
import com.rupeeboss.rba.core.response.PropertyResponse;

import retrofit2.Call;
import retrofit2.http.POST;


/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class SyncRequestBuilder extends RetroRequestBuilder {

    public SyncNetworkService getService() {
        return super.build().create(SyncNetworkService.class);
    }

    public interface SyncNetworkService {

        @POST("/api/getcity")
        Call<CityResponse> getCity();

        @POST("/api/getproduct")
        Call<ProductResponse> getProduct();

        @POST("/api/getproperty")
        Call<PropertyResponse> getProperty();
    }
}
