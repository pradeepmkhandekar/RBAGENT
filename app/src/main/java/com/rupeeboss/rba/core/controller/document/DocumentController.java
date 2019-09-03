package com.rupeeboss.rba.core.controller.document;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.DocumentRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.UploadDocumentRequest;
import com.rupeeboss.rba.core.response.DocumentResponse;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.UploadDocumentResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajeev Ranjan on 15/05/2017.
 */

public class DocumentController implements IDocument {

    DocumentRequestBuilder.DocumentNetworkService documentNetworkService;
    Context mContext;

    public DocumentController(Context context) {
        documentNetworkService = new DocumentRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getDocumentRequired(final IResponseSubcriber iResponseSubcriber) {

        documentNetworkService.getRequiredDocuments().enqueue(new Callback<DocumentResponse>() {
            @Override
            public void onResponse(Call<DocumentResponse> call, Response<DocumentResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatusId() == 0) {
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
            public void onFailure(Call<DocumentResponse> call, Throwable t) {

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
    public void getuploadCustDocuments(UploadDocumentRequest uploaddocumentRequest, final IResponseSubcriber iResponseSubcriber) {

        documentNetworkService.getuploadCustDocuments(uploaddocumentRequest).enqueue(new Callback<UploadDocumentResponse>() {
            @Override
            public void onResponse(Call<UploadDocumentResponse> call, Response<UploadDocumentResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatusId() == 0) {
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
            public void onFailure(Call<UploadDocumentResponse> call, Throwable t) {

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
