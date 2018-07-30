package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.DocumentEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 15/05/2017.
 */

public class DocumentResponse extends APIResponse {
    /**
     * result : {"lstprodReqDoc":[{"ProdName":"Home Loan","docDtls":["Passport Size photographs","licence/ IT PAN card","Proof of identify"],"prodId":12},{"ProdName":"Personal Loan","docDtls":["Proof of identify","Latest 3 months Bank Statement"],"prodId":9}]}
     */

    private DocumentResult result;

    public DocumentResult getResult() {
        return result;
    }

    public void setResult(DocumentResult result) {
        this.result = result;
    }

    public class DocumentResult {
        private List<DocumentEntity> lstprodReqDoc;

        public List<DocumentEntity> getLstprodReqDoc() {
            return lstprodReqDoc;
        }

        public void setLstprodReqDoc(List<DocumentEntity> lstprodReqDoc) {
            this.lstprodReqDoc = lstprodReqDoc;
        }


    }
}
