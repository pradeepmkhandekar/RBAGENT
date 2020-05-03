package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.FestivalCompaignEntity;

import java.util.List;

public class FestivalCampaignResponse extends APIResponse {


    private List<FestivalCompaignEntity> MasterData;

    public List<FestivalCompaignEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<FestivalCompaignEntity> MasterData) {
        this.MasterData = MasterData;
    }

    public int getStatusNo() {
        return StatusNo;
    }

    public void setStatusNo(int statusNo) {
        StatusNo = statusNo;
    }

    public int StatusNo;
}