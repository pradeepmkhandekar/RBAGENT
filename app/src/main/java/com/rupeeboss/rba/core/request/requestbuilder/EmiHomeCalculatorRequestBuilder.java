package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeEmiCalRequest;

import com.rupeeboss.rba.core.response.EmiHomeCalcResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by IN-RB on 15-06-2017.
 */

public class EmiHomeCalculatorRequestBuilder extends RetroRequestBuilder {

    public EmiHomeCalculatorNetworkService getService() {
        return super.build().create(EmiHomeCalculatorNetworkService.class);
    }
    public interface EmiHomeCalculatorNetworkService{

        @POST("/api/productwise_emi_cal_app")
        Call<EmiHomeCalcResponse> getemihomecalculatordata(@Body HomeEmiCalRequest homeLoanRequest);

    }
}
