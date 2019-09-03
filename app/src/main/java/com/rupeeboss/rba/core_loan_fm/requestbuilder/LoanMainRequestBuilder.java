package com.rupeeboss.rba.core_loan_fm.requestbuilder;

import com.rupeeboss.rba.core_loan_fm.FinmartRetroRequestBuilder;
import com.rupeeboss.rba.core_loan_fm.requestentity.BankSaveRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmBalanceLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmHomeLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmPersonalLoanRequest;
import com.rupeeboss.rba.core_loan_fm.response.BankForNodeResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmBalanceLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmHomelLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmPersonalLoanResponse;
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

        @Headers("token:1234567890")
        @POST("/api/get-loan-request")
        Call<FmHomelLoanResponse> getHLQuoteApplication(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
//        @POST("/api/save-HLloan-request")
        @POST("/api/save-loan-request")
        Call<FmSaveQuoteHomeLoanResponse> saveHLQuote(@Body FmHomeLoanRequest fmHomeLoanRequest);


        //endregion

        //    region PersonalLoan


        @Headers("token:1234567890")
        @POST("/api/getloanrequest")
        Call<NewLoanApplicationResponse> getLoanApplication(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
        @POST("/api/get-personalloan-request")
        Call<FmPersonalLoanResponse> getPLQuoteApplication(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
        @POST("/api/manage-personalloan")
        Call<FmSaveQuotePersonalLoanResponse> savePLQuote(@Body FmPersonalLoanRequest fmPersonalLoanRequest);

        //endregion

        //bank save
        @Headers("token:1234567890")
        @POST("/api/update-bank-id")
        Call<BankForNodeResponse> savebankFbABuy(@Body BankSaveRequest bankSaveRequest);

        //BT


        @Headers("token:1234567890")
        @POST("/api/get-balance-transfer-request")
        Call<FmBalanceLoanResponse> getBLQuoteApplication(@Body HashMap<String, String> body);

        @Headers("token:1234567890")
        @POST("/api/ManageBalanceTransfer")
        Call<FmSaveQuoteBLResponse> saveBLQuote(@Body FmBalanceLoanRequest fmBalanceLoanRequest);
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

    }

}
