package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by IN-RB on 21-08-2017.
 */

public class UploadDocumentRequest {

    /**
     * refFBAId : 1
     * docType : PanCard
     * docextension :
     * bytes :
     */

    private int refFBAId;
    private String docType;
    private String docextension;
    private String bytes;

    public int getRefFBAId() {
        return refFBAId;
    }

    public void setRefFBAId(int refFBAId) {
        this.refFBAId = refFBAId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocextension() {
        return docextension;
    }

    public void setDocextension(String docextension) {
        this.docextension = docextension;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }
}
