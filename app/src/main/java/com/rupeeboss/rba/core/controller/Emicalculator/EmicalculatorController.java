package com.rupeeboss.rba.core.controller.Emicalculator;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EmiCalculatorRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.BuisnessLoanCalRequest;
import com.rupeeboss.rba.core.request.requestentity.WorkCapitalEmiRequestEntity;
import com.rupeeboss.rba.core.response.BLQuoteResponse;
import com.rupeeboss.rba.core.response.BuisnessLoanCalResponse;
import com.rupeeboss.rba.core.response.EmiCalculatorResponse;
import com.rupeeboss.rba.core.response.WorkCapitalResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 07-06-2017.
 */

public class EmicalculatorController implements IEmicalculator {
    Context mContext;
    EmiCalculatorRequestBuilder.EmiCalculatorNetworkService emiCalculatorNetworkService;

    public EmicalculatorController(Context context) {
        this.mContext = context;
        emiCalculatorNetworkService = new EmiCalculatorRequestBuilder().getService();

    }

    @Override
    public void getEmicalculatordata(String loanamount, String rateofint, String loantensure, final IResponseSubcriber iResponseSubcriber) {


        HashMap<String, String> bodyParameter = new HashMap<String, String>();

        bodyParameter.put("loanamount", loanamount);
        bodyParameter.put("loaninterest", rateofint);
        // bodyParameter.put("empcode", new LoginFacade(mContext).getUser().getEmpCode());
        bodyParameter.put("loanterm", loantensure);

        emiCalculatorNetworkService.getemicalculatordata(bodyParameter).enqueue(new Callback<EmiCalculatorResponse>() {
            @Override
            public void onResponse(Response<EmiCalculatorResponse> response, Retrofit retrofit) {

                try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }
                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });


    }

    @Override
    public void getWorkingCapital(WorkCapitalEmiRequestEntity workCapitalEmiRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        emiCalculatorNetworkService.getWorkingCapital(workCapitalEmiRequestEntity).enqueue(new Callback<WorkCapitalResponse>() {
            @Override
            public void onResponse(Response<WorkCapitalResponse> response, Retrofit retrofit) {

                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getErr_code());
                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });

    }

    @Override
    public void getBuisnessLoan(BuisnessLoanCalRequest buisnessLoanCalRequest, final IResponseSubcriber iResponseSubcriber) {

        emiCalculatorNetworkService.getBuisnessLoan(buisnessLoanCalRequest).enqueue(new Callback<BuisnessLoanCalResponse>() {
            @Override
            public void onResponse(Response<BuisnessLoanCalResponse> response, Retrofit retrofit) {

                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getErr_code());
                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });
    }

    @Override
    public void getBLQuotes(BuisnessLoanCalRequest buisnessLoanCalRequest, final IResponseSubcriber iResponseSubcriber) {
        emiCalculatorNetworkService.getBLQuotes(buisnessLoanCalRequest).enqueue(new Callback<BLQuoteResponse>() {
            @Override
            public void onResponse(Response<BLQuoteResponse> response, Retrofit retrofit) {

                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Server down ! Please Try after sometime.."));
                }
            }
        });
    }


}
