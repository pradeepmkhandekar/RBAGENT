package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.core.response.GetQuoteResponse;
import com.rupeeboss.rba.core.response.PersonalQuoteAppDispalyResponse;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by IN-RB on 07-02-2017.
 */

public class PersonalloanRequestBuilder  extends RetroRequestBuilder {

    public PersonalloanNetworkService getService() {
        return super.build().create(PersonalloanNetworkService.class);
    }
    public interface PersonalloanNetworkService{

        @POST("/api/personal-loan-mobile")
        Call<GetPersonalLoanResponse> getPersonalQuotes(@Body PersonalLoanRequest personalLoanRequest);

        @POST("/api/get-personal-loan-broker-quote")
        Call<PersonalQuoteAppDispalyResponse> getPersonalBrokerQuotes(@Body HashMap<String, String> bodyParameter);


    }
}
