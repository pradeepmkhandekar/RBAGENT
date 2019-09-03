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
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.core.response.WorkCapitalResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            public void onResponse(Call<EmiCalculatorResponse> call, Response<EmiCalculatorResponse> response) {

                try {
                    if (response.body() != null) {


                        if (response.body().getStatus_Id() == 0) {
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());


                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
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
            public void onFailure(Call<EmiCalculatorResponse> call, Throwable t) {

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



    @Override
    public void getWorkingCapital(WorkCapitalEmiRequestEntity workCapitalEmiRequestEntity, final IResponseSubcriber iResponseSubcriber) {


        emiCalculatorNetworkService.getWorkingCapital(workCapitalEmiRequestEntity).enqueue(new Callback<WorkCapitalResponse>() {
            @Override
            public void onResponse(Call<WorkCapitalResponse> call, Response<WorkCapitalResponse> response) {

                try {
                    if (response.body() != null) {


                        iResponseSubcriber.OnSuccess(response.body(), response.body().getErr_code());

                    } else {
                        //failure
                        iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<WorkCapitalResponse> call, Throwable t) {

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

    @Override
    public void getBuisnessLoan(BuisnessLoanCalRequest buisnessLoanCalRequest, final IResponseSubcriber iResponseSubcriber) {

        emiCalculatorNetworkService.getBuisnessLoan(buisnessLoanCalRequest).enqueue(new Callback<BuisnessLoanCalResponse>() {
            @Override
            public void onResponse(Call<BuisnessLoanCalResponse> call, Response<BuisnessLoanCalResponse> response) {

                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getErr_code());


                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<BuisnessLoanCalResponse> call, Throwable t) {

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

    @Override
    public void getBLQuotes(BuisnessLoanCalRequest buisnessLoanCalRequest, final IResponseSubcriber iResponseSubcriber) {


        emiCalculatorNetworkService.getBLQuotes(buisnessLoanCalRequest).enqueue(new Callback<BLQuoteResponse>() {
            @Override
            public void onResponse(Call<BLQuoteResponse> call, Response<BLQuoteResponse> response) {

                try {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<BLQuoteResponse> call, Throwable t) {

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
