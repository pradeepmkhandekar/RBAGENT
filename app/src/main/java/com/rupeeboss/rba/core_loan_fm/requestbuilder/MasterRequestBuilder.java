package com.rupeeboss.rba.core_loan_fm.requestbuilder;


import com.rupeeboss.rba.core_loan_fm.response.CityResponse;
import com.rupeeboss.rba.core_loan_fm.response.quickresponse;
import com.rupeeboss.rba.core_loan_fm.retrobuilder.LoanRetroRequestBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

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

        @POST
        Call<quickresponse> getNCD(@Url String strUrl, @Body HashMap<String, String> body);
    }
}
