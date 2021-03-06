package com.rupeeboss.rba.core_loan_fm.requestbuilder;


import com.rupeeboss.rba.core_loan_fm.requestentity.BLLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.equifax_personalloan_request;
import com.rupeeboss.rba.core_loan_fm.response.GetBLDispalyResponse;
import com.rupeeboss.rba.core_loan_fm.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.equifax_personalloan_response;
import com.rupeeboss.rba.core_loan_fm.retrobuilder.LoanRetroRequestBuilder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by IN-RB on 15-01-2018.
 */

public class PersonalloanRequestBuilder extends LoanRetroRequestBuilder {

    public PersonalloanNetworkService getService() {
        return super.build().create(PersonalloanNetworkService.class);
    }
    public interface PersonalloanNetworkService{
        //  personal-loan-mobile//
        @POST("/api/customer-loan-request")
        Call<GetPersonalLoanResponse> getPersonalQuotes(@Body PersonalLoanRequest personalLoanRequest);


        @POST("/api/balance-transfer-with-qid")
        Call<GetBLDispalyResponse> getBLDispalyResponseQuotes(@Body BLLoanRequest blLoanRequest);

        @POST("/api/equifax")
        Call<equifax_personalloan_response> getequifaxQuotes(@Body equifax_personalloan_request equifax_personalloan_requestlist);

    }
}
