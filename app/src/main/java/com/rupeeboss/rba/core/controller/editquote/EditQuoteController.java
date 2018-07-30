package com.rupeeboss.rba.core.controller.editquote;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.EditQuoteRequestBuilder;
import com.rupeeboss.rba.core.response.EditQuoteResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
            public void onResponse(Response<EditQuoteResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (iResponseSubcriber != null) {
                        if (response.body().getStatus_Id() == 0) {
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.message());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                        }
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.message()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Server down,Try after sometime..."));
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
