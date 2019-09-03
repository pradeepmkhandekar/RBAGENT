package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core.model.CityEntity;
import com.rupeeboss.rba.core_loan_fm.APIResponse;

import java.util.List;


public class CityResponse extends APIResponse {


    private List<CityEntity> data;

    public List<CityEntity> getData() {
        return data;
    }

    public void setData(List<CityEntity> data) {
        this.data = data;
    }


}