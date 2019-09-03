package com.rupeeboss.rba.core_loan_fm.requestbuilder;


import com.rupeeboss.rba.core_loan_fm.requestentity.ErpHomeLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.ErpPersonLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.HomeLoanApplyRequestEntity;
import com.rupeeboss.rba.core_loan_fm.response.ERPSaveResponse;
import com.rupeeboss.rba.core_loan_fm.response.GenerateHLLeadResponse;
import com.rupeeboss.rba.core_loan_fm.response.HomeLoanApplicationResponse;
import com.rupeeboss.rba.core_loan_fm.response.LeadResponse;
import com.rupeeboss.rba.core_loan_fm.response.LoanCityResponse;
import com.rupeeboss.rba.core_loan_fm.response.PersonalLoanApplicationResponse;
import com.rupeeboss.rba.core_loan_fm.response.ShareMessageResponse;
import com.rupeeboss.rba.core_loan_fm.response.citywisebankloanResponse;
import com.rupeeboss.rba.core_loan_fm.retrobuilder.ERPRetroRequestBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by IN-RB on 04-03-2018.
 */

public class ERPRequestBuilder extends ERPRetroRequestBuilder {

    public ERPRequestBuilder.ERPNetworkService getService() {
        return super.build().create(ERPRequestBuilder.ERPNetworkService.class);
    }

    public interface ERPNetworkService {

        @POST("/LoginDtls.svc/XMLService/insHomeLoanApplnDtlsForAPP")
        Call<ERPSaveResponse> saveErpHomeLoanData(@Body ErpHomeLoanRequest erpLoanRequest);

        @POST("/LoginDtls.svc/XMLService/insLAPDtlsForAPP")
        Call<ERPSaveResponse> saveErpHoLoanAgainstPropertyData(@Body ErpHomeLoanRequest erpLoanRequest);

        @POST("/LoginDtls.svc/XMLService/insPersonalLoanApplnDtlsForAPP")
        Call<ERPSaveResponse> saveErpPersonalLoanData(@Body ErpPersonLoanRequest erpLoanRequest);


        @POST("/LoginDtls.svc/XMLService/dsplyHomeloanDtlsForAPP")
        Call<HomeLoanApplicationResponse> getHomeLoanApplication(@Body HashMap<String, String> body);

        @POST("/LoginDtls.svc/XMLService/dsplypersonalloanDtlsForAPP")
        Call<PersonalLoanApplicationResponse> getPersonalLoanApplication(@Body HashMap<String, String> body);

        @POST("/LoginDtls.svc/XMLService/dsplyMsgLnkDtlsForFinmart")
        Call<ShareMessageResponse> getShareData(@Body HashMap<String, String> body);

        @POST("/LoginDtls.svc/XMLService/getleadHistory")
        Call<LeadResponse> getLeadDetail(@Body HashMap<String, String> body);


        @POST("/LoginDtls.svc/XMLService/genrateLeadFrmHLAppln")
        Call<GenerateHLLeadResponse> generateLead(@Body HomeLoanApplyRequestEntity body);

        @POST("/LoginDtls.svc/XMLService/dsplyCityDtls")
        Call<LoanCityResponse> getcityloan();

        @POST("/LoginDtls.svc/XMLService/GetCitywiseBankList")
        Call<citywisebankloanResponse> getCitywiseBankListloan(@Body HashMap<String, String> body);
    }
}
