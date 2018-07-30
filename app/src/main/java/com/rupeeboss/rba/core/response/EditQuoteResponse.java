package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.CustomerEntity;

/**
 * Created by Rajeev Ranjan on 02/03/2017.
 */

public class EditQuoteResponse extends APIResponse {
    /**
     * data : {"status":"Success","ID":213,"PropertyID":"6","PropertyCost":"6000000","LoanTenure":"20","LoanRequired":"4000000","City":"mumbai","ApplicantNme":"user","Email":null,"Contact":null,"ApplicantGender":"M","ApplicantSource":"1","ApplicantIncome":"100000","ApplicantObligations":"0","ApplicantDOB":"1985-12-12","CoApplicantYes":"N","CoApplicantGender":"","CoApplicantSource":"","CoApplicantIncome":"","CoApplicantObligations":"0","CoApplicantDOB":"","Turnover":"0","ProfitAfterTax":"0","Depreciation":"0","DirectorRemuneration":"0","CoApplicantTurnover":"0","CoApplicantProfitAfterTax":"0","CoApplicantDepreciation":"0","CoApplicantDirectorRemuneration":"0","BrokerId":null,"ProductId":7,"bank_id":null,"roi_type":null,"loan_eligible":null,"processing_fee":null,"created_at":"2016-12-27 07:38:59","updated_at":"2017-01-02 09:47:14"}
     */

    private CustomerEntity data;

    public CustomerEntity getData() {
        return data;
    }

    public void setData(CustomerEntity data) {
        this.data = data;
    }


}
