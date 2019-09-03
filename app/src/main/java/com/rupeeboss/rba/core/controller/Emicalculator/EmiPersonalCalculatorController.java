package com.rupeeboss.rba.core.controller.Emicalculator;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EmiHomeCalculatorRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.HomeEmiCalRequest;
import com.rupeeboss.rba.core.response.EmiHomeCalcResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IN-RB on 29-06-2017.
 */

public class EmiPersonalCalculatorController  implements IEmiPersonalCalculator{
    Context mContext;
    EmiHomeCalculatorRequestBuilder.EmiHomeCalculatorNetworkService  emiHomeCalculatorNetworkService;
    public EmiPersonalCalculatorController(Context context)
    {
        this.mContext = context;
        emiHomeCalculatorNetworkService = new EmiHomeCalculatorRequestBuilder().getService();
    }

    @Override
    public void getEmiPersonalcalculatordata(HomeEmiCalRequest homeLoanRequest , final IResponseSubcriber iResponseSubcriber) {


        emiHomeCalculatorNetworkService.getemihomecalculatordata(homeLoanRequest).enqueue(new Callback<EmiHomeCalcResponse>() {
            @Override
            public void onResponse(Call<EmiHomeCalcResponse> call, Response<EmiHomeCalcResponse> response) {

                try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getErr_code());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getErr_code()));
                    }


                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<EmiHomeCalcResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });





    }



}
