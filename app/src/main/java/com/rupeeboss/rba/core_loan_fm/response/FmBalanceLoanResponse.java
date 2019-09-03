package com.rupeeboss.rba.core_loan_fm.response;

import com.rupeeboss.rba.core_loan_fm.APIResponseFM;
import com.rupeeboss.rba.core_loan_fm.model.BLNodeMainEntity;

/**
 * Created by IN-RB on 25-02-2018.
 */

public class FmBalanceLoanResponse extends APIResponseFM {
    private BLNodeMainEntity MasterData;

    public BLNodeMainEntity getMasterData() {
        return MasterData;
    }
    public void setMasterData(BLNodeMainEntity MasterData) {
        this.MasterData = MasterData;
    }
}
