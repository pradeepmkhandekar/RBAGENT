package com.rupeeboss.rba.core_loan_fm.response;


import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.model.PersonalLoanApplyAppliEntity;

/**
 * Created by IN-RB on 16-03-2018.
 */

public class PersonalLoanApplicationResponse extends APIResponseERP {

    private int result;
    private PersonalLoanApplyAppliEntity data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public PersonalLoanApplyAppliEntity getData() {
        return data;
    }

    public void setData(PersonalLoanApplyAppliEntity data) {
        this.data = data;
    }
}
