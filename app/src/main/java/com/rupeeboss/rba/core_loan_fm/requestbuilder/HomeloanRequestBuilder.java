package com.rupeeboss.rba.core_loan_fm.requestbuilder;


import com.rupeeboss.rba.core_loan_fm.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core_loan_fm.response.GetQuoteResponse;
import com.rupeeboss.rba.core_loan_fm.response.RBCustomerResponse;
import com.rupeeboss.rba.core_loan_fm.retrobuilder.LoanRetroRequestBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class HomeloanRequestBuilder extends LoanRetroRequestBuilder {

    public HomeloanNetworkService getService() {
        return super.build().create(HomeloanNetworkService.class);
    }

    public interface HomeloanNetworkService {

        @POST("/api/mobile-api-compare")
        Call<GetQuoteResponse> getQuotes(@Body HomeLoanRequest homeLoanRequest);

        @POST("/api/getcustomer")
        Call<RBCustomerResponse> getRupeeBossCustomer(@Body HashMap<String, String> body);



    }
}
