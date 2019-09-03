package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core_loan_fm.APIResponseFM;
import com.rupeeboss.rba.core_loan_fm.model.PersonalMainEntity;

/**
 * Created by IN-RB on 01-02-2018.
 */

public class FmPersonalLoanResponse extends APIResponseFM {
    private PersonalMainEntity MasterData;

    public PersonalMainEntity getMasterData() {
        return MasterData;
    }
    public void setMasterData(PersonalMainEntity MasterData) {
        this.MasterData = MasterData;
    }

}
