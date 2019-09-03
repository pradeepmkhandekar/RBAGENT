package com.rupeeboss.rba.core.controller.editquote;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EditQuoteRequestBuilder;
import com.rupeeboss.rba.core.response.EditQuoteResponse;
import com.rupeeboss.rba.core.response.MyBusinessResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 02/03/2017.
 */

public class EditQuoteController implements IEditQuote {
    Context mContext;
    EditQuoteRequestBuilder.EditQuoteNetworkService editQuoteNetworkService;

    public EditQuoteController(Context mContext) {
        this.mContext = mContext;
        editQuoteNetworkService = new EditQuoteRequestBuilder().getService();
    }

    @Override
    public void getCustomerDetails(String ID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> bodyparameter = new HashMap<String, String>();
        bodyparameter.put("ID", ID);

        editQuoteNetworkService.getDisplayQuotes(bodyparameter).enqueue(new Callback<EditQuoteResponse>() {
            @Override
            public void onResponse(Call<EditQuoteResponse> call, Response<EditQuoteResponse> response) {

                try {
                    if (response.body() != null) {


                        if (response.body().getStatus_Id() == 0) {
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());


                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
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
            public void onFailure(Call<EditQuoteResponse> call, Throwable t) {

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
