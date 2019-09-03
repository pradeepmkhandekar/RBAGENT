package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.model.LstCitywiseBankLoanEntity;

import java.util.List;

public class citywisebankloanResponse extends APIResponseERP {

    private List<LstCitywiseBankLoanEntity> result;

    public List<LstCitywiseBankLoanEntity> getResult() {
        return result;
    }

    public void setResult(List<LstCitywiseBankLoanEntity> result) {
        this.result = result;
    }


}
