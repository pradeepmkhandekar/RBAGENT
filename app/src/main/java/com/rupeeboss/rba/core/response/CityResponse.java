package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.CityEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class CityResponse extends APIResponse {


    private List<CityEntity> data;

    public List<CityEntity> getData() {
        return data;
    }

    public void setData(List<CityEntity> data) {
        this.data = data;
    }


}
