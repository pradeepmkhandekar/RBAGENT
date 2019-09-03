package com.rupeeboss.rba.core.controller.quote;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.QuoteRequestBuilder;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;
import com.rupeeboss.rba.core.response.QuoteSelectedResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuoteController implements IDisplayQuote {

    Context mContext;
    QuoteRequestBuilder.QuoteNetworkService quoteNetworkService;

    public QuoteController(Context context) {
        this.mContext = context;
        quoteNetworkService = new QuoteRequestBuilder().getService();
    }

    @Override
    public void getQuote(int ProductId, String BrokerID, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        if (BrokerID.matches("0") || BrokerID.matches(""))
            BrokerID = "-1";
        bodyParameter.put("BrokerId", BrokerID);
        bodyParameter.put("flag", "RBAPP");
        bodyParameter.put("empcode", new LoginFacade(mContext).getUser().getEmpCode());
        bodyParameter.put("ProductId", "" + ProductId);

        quoteNetworkService.getDisplayQuotes(bodyParameter).enqueue(new Callback<QuoteDisplayResponse>() {
            @Override
            public void onResponse(Call<QuoteDisplayResponse> call, Response<QuoteDisplayResponse> response) {

                try {
                    if (response.body().getStatus_Id()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());


                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<QuoteDisplayResponse> call, Throwable t) {

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
    public void sendSelectedQuoteInfo(String quoteId, String bankId, String roiType, String loanEligible, String processingFees) {

        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        bodyParameter.put("quote_id", quoteId);
        bodyParameter.put("bank_id", bankId);
        bodyParameter.put("roi_type", roiType);
        bodyParameter.put("loan_eligible", loanEligible);
        bodyParameter.put("processing_fee", processingFees);

        quoteNetworkService.sendSelectedQuoteToServer(bodyParameter).enqueue(new Callback<QuoteSelectedResponse>() {
            @Override
            public void onResponse(Call<QuoteSelectedResponse> call, Response<QuoteSelectedResponse> response) {

                  /*try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }*/
            }

            @Override
            public void onFailure(Call<QuoteSelectedResponse> call, Throwable t) {

                    /*if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }*/
            }
        });

    }


}
