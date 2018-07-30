package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.UploadDocumentRequest;
import com.rupeeboss.rba.core.response.DocumentResponse;
import com.rupeeboss.rba.core.response.UploadDocumentResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 15/05/2017.
 */

public class DocumentRequestBuilder extends WCFRetroRequestBuilder {
    public DocumentRequestBuilder.DocumentNetworkService getService() {

        return super.build().create(DocumentRequestBuilder.DocumentNetworkService.class);
    }

    public interface DocumentNetworkService {
        @POST("/LoginDtls.svc/XMLService/dsplyRequiredDosDtlsForProduct")
        Call<DocumentResponse> getRequiredDocuments();


        @POST("/LoginDtls.svc/XMLService/upldCustLoanDocuments ")
        Call<UploadDocumentResponse> getuploadCustDocuments(@Body UploadDocumentRequest uploaddocumentRequestentity);

    }

}
