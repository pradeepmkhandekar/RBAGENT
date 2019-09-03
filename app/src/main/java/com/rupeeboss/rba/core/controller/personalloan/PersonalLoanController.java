package com.rupeeboss.rba.core.controller.personalloan;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestbuilder.PersonalloanRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.core.response.PersonalQuoteAppDispalyResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            public void onResponse(Call<GetPersonalLoanResponse> call, Response<GetPersonalLoanResponse> response) {

                try {


                    if (response.body().getStatus_Id()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<GetPersonalLoanResponse> call, Throwable t) {

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
            public void onResponse(Call<PersonalQuoteAppDispalyResponse> call, Response<PersonalQuoteAppDispalyResponse> response) {

                try {
                    if (response.body().getStatus_Id()  == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<PersonalQuoteAppDispalyResponse> call, Throwable t) {

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
