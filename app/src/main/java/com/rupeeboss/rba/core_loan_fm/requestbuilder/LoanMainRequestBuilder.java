package com.rupeeboss.rba.core_loan_fm.requestbuilder;

import com.rupeeboss.rba.core.response.PincodeResponse;
import com.rupeeboss.rba.core_loan_fm.FinmartRetroRequestBuilder;
import com.rupeeboss.rba.core_loan_fm.requestentity.BankSaveRequest;
import com.rupeeboss.rba.core_loan_fm.response.BankForNodeResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuoteBLResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuoteHomeLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuotePersonalLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.NewLoanApplicationResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by IN-RB on 31-01-2018.
 */

public class LoanMainRequestBuilder extends FinmartRetroRequestBuilder {

    public LoanMainNetworkService getService() {

        return super.build().create(LoanMainNetworkService.class);
    }

    public interface LoanMainNetworkService {

        //    region HomeLoan


        //endregion

        //    region PersonalLoan


        @Headers("token:1234567890")
        @POST("/api/getrbaloanrequest")
        Call<NewLoanApplicationResponse> getLoanApplication(@Body HashMap<String, String> body);


        //endregion

        //bank save
        @Headers("token:1234567890")
        @POST("/api/update-bank-id")
        Call<BankForNodeResponse> savebankFbABuy(@Body BankSaveRequest bankSaveRequest);

        //BT

      //delete
        @Headers("token:1234567890")
        @POST("/api/delete-loan-request-loan")
        Call<FmSaveQuoteHomeLoanResponse> getdelete_loanrequest(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
        @POST("/api/delete-personal-loan-request")
        Call<FmSaveQuotePersonalLoanResponse> getdelete_personalrequest(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
        @POST("/api/delete-balance-transfer")
        Call<FmSaveQuoteBLResponse> getdelete_balancerequest(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/get-city-and-state")
        Call<PincodeResponse> getCityStateCityPincode(@Body HashMap<String, String> body);

    }

}
