package com.rupeeboss.rba.core.controller.Emicalculator;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EmiRecalculationRequestBuilder;
import com.rupeeboss.rba.core.response.EmiRecalculationResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IN-RB on 06-07-2017.
 */

public class EmiRecalculationController  implements IEmiRecalculation{
    Context mContext;

    EmiRecalculationRequestBuilder.EmiRecalculationNetworkService EmiRecalculationNetworkService;

    public EmiRecalculationController(Context context) {
        this.mContext = context;
        EmiRecalculationNetworkService = new EmiRecalculationRequestBuilder().getService();

    }



    @Override
    public void getEmiRecalculationdata(String loanamount, String rateofint, String loantensure, String old_loaninterest,final IResponseSubcriber iResponseSubcriber) {
            HashMap<String, String> bodyParameter = new HashMap<String, String>();

            bodyParameter.put("loanamount", loanamount);
            bodyParameter.put("loaninterest", rateofint);
            // bodyParameter.put("empcode", new LoginFacade(mContext).getUser().getEmpCode());
            bodyParameter.put("loanterm", loantensure);
            bodyParameter.put("old_loaninterest", old_loaninterest);

        EmiRecalculationNetworkService.getEmiRecalculationdata(bodyParameter).enqueue(new Callback<EmiRecalculationResponse>() {
            @Override
            public void onResponse(Call<EmiRecalculationResponse> call, Response<EmiRecalculationResponse> response) {

                try {
                    if (response.body() != null) {


                        if (response.body().getStatus_Id() == 0) {
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getErr());
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getErr()));
                        }
                    } else {
                        //failure
                        iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<EmiRecalculationResponse> call, Throwable t) {

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
