package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core_loan_fm.APIResponseFM;
import com.rupeeboss.rba.core_loan_fm.model.NewLoanApplicationEnity;

import java.util.List;

/**
 * Created by IN-RB on 18-03-2019.
 */

public class NewLoanApplicationResponse extends APIResponseFM {

    private List<NewLoanApplicationEnity> MasterData;

    public List<NewLoanApplicationEnity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<NewLoanApplicationEnity> MasterData) {
        this.MasterData = MasterData;
    }


}
