package com.rupeeboss.rba.core.controller.document;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.UploadDocumentRequest;


/**
 * Created by Rajeev Ranjan on 15/05/2017.
 */

public interface IDocument {
    void getDocumentRequired(IResponseSubcriber iResponseSubcriber);

    void getuploadCustDocuments(UploadDocumentRequest uploaddocumentRequest, IResponseSubcriber iResponseSubcriber);
}
