package com.rupeeboss.rba.core.controller.personalloan;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.PersonalloanRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.core.response.PersonalQuoteAppDispalyResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 10-02-2017.
 */

public class PersonalLoanController implements IPersonalLoan {

    Context mContext;
    PersonalloanRequestBuilder.PersonalloanNetworkService personalloanNetworkService;

    public PersonalLoanController(Context context) {
        this.mContext = context;
        personalloanNetworkService = new PersonalloanRequestBuilder().getService();
    }

    @Override
    public void getPersonalLoan(PersonalLoanRequest personalLoanRequest, final IResponseSubcriber iResponseSubcriber) {
        personalloanNetworkService.getPersonalQuotes(personalLoanRequest).enqueue(new Callback<GetPersonalLoanResponse>() {
            @Override
            public void onResponse(Response<GetPersonalLoanResponse> response, Retrofit retrofit) {
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
    public void getPersonalQuote(int ProductId, String BrokerID, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameter = new HashMap<String, String>();
        if (BrokerID.matches("0") || BrokerID.matches(""))
            BrokerID = "-1";
        bodyParameter.put("BrokerId", BrokerID);
        bodyParameter.put("flag", "RBAPP");
        bodyParameter.put("empcode", new LoginFacade(mContext).getUser().getEmpCode());
        bodyParameter.put("ProductId", "" + ProductId);

        personalloanNetworkService.getPersonalBrokerQuotes(bodyParameter).enqueue(new Callback<PersonalQuoteAppDispalyResponse>() {
            @Override
            public void onResponse(Response<PersonalQuoteAppDispalyResponse> response, Retrofit retrofit) {

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
}
