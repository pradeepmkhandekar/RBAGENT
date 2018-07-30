package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.BuisnessLoanCalRequest;
import com.rupeeboss.rba.core.request.requestentity.WorkCapitalEmiRequestEntity;
import com.rupeeboss.rba.core.response.BLQuoteResponse;
import com.rupeeboss.rba.core.response.BuisnessLoanCalResponse;
import com.rupeeboss.rba.core.response.EmiCalculatorResponse;
import com.rupeeboss.rba.core.response.WorkCapitalResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 07-06-2017.
 */

public class EmiCalculatorRequestBuilder extends RetroRequestBuilder {

    public EmiCalculatorNetworkService getService() {
        return super.build().create(EmiCalculatorNetworkService.class);
    }

    public interface EmiCalculatorNetworkService {

        @POST("/api/emi-calc-app")
        Call<EmiCalculatorResponse> getemicalculatordata(@Body HashMap<String, String> bodyParameter);

        @POST("/api/working-capital-emi-calculator-api")
        Call<WorkCapitalResponse> getWorkingCapital(@Body WorkCapitalEmiRequestEntity workCapitalEmiRequestEntity);

        @POST("/api/productwise_emi_cal_app")
        Call<BuisnessLoanCalResponse> getBuisnessLoan(@Body BuisnessLoanCalRequest buisnessLoanCalRequest);

        @POST("/api/bussiness-loan-quote")
        Call<BLQuoteResponse> getBLQuotes(@Body BuisnessLoanCalRequest buisnessLoanCalRequest);


    }
}
