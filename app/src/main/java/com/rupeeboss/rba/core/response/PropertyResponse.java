package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.PropertyEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 30-01-2017.
 */

public class PropertyResponse extends APIResponse {

    private List<PropertyEntity> data;

    public List<PropertyEntity> getData() {
        return data;
    }

    public void setData(List<PropertyEntity> data) {
        this.data = data;
    }


}
