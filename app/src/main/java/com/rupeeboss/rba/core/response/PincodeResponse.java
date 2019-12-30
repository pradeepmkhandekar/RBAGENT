package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core_loan_fm.requestentity.PincodeResponseEntity;

/**

 */

public class PincodeResponse extends APIResponse {

    /**
     * HealthMasterData : {"map_id":34000,"pincode":"805110","postname":"Nawadah","districtname":"Nawada","stateid":5,"state_name":"BIHAR","cityid":247,"cityname":"Nawada"}
     */

    private PincodeResponseEntity MasterData;

    public PincodeResponseEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(PincodeResponseEntity MasterData) {
        this.MasterData = MasterData;
    }


}
