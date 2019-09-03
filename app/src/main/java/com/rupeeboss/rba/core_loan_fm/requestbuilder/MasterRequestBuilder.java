package com.rupeeboss.rba.core_loan_fm.requestbuilder;


import com.rupeeboss.rba.core_loan_fm.response.CityResponse;
import com.rupeeboss.rba.core_loan_fm.retrobuilder.LoanRetroRequestBuilder;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Rajeev Ranjan on 13/02/2018.
 */

public class MasterRequestBuilder extends LoanRetroRequestBuilder {
    public MasterRequestBuilder.MasterloanNetworkService getService() {
        return super.build().create(MasterRequestBuilder.MasterloanNetworkService.class);
    }

    public interface MasterloanNetworkService {

            @POST("/api/getcity")
            Call<CityResponse> getCity();
    }
}
