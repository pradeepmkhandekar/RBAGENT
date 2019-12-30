package com.rupeeboss.rba.core_loan_fm.response;

import com.rupeeboss.rba.core_loan_fm.APIResponse;

public class quickresponse extends APIResponse {

    /**
     * Status : 1
     * Lead_Id : 1675532
     */

    private String Status;
    private String Lead_Id;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getLead_Id() {
        return Lead_Id;
    }

    public void setLead_Id(String Lead_Id) {
        this.Lead_Id = Lead_Id;
    }
}
