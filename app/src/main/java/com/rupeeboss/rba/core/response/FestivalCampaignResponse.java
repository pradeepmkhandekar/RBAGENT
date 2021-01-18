package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.FestivalCompaignEntity;

import java.util.List;

public class FestivalCampaignResponse extends APIResponse {


    private MasterData MasterData;

    private String Message;
    private String Status;
    private Long StatusNo;

    public MasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterData masterData) {
        MasterData = masterData;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Long getStatusNo() {
        return StatusNo;
    }

    public void setStatusNo(Long statusNo) {
        StatusNo = statusNo;
    }
}