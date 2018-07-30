package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.CustomerApplicationEntity;

/**
 * Created by Rajeev Ranjan on 06/03/2017.
 */

public class EditApplicationResponse extends APIResponse {

    /**
     * data : {"AppId":4982,"ApplicantDOB":"08/09/1987","ApplicantGender":"Male","ApplicantIncome":"245000","ApplicantNme":null,"ApplicantObligations":"","ApplnStatus":"Complete","ApplntName":"Sanajy Shrivastav Shah","BrokerId":"0","City":"THANE","CoApplicantDOB":"","CoApplicantDepreciation":"","CoApplicantDirectorRemuneration":"","CoApplicantGender":"Female","CoApplicantIncome":"","CoApplicantObligations":"","CoApplicantProfitAfterTax":"","CoApplicantSource":null,"CoApplicantTurnover":"","CoApplicantYes":"0","Contact":"9865545450","Depreciation":"","DirectorRemuneration":"","Email":"ajay@insuremagic.com","Gross_Income":"0","LoanRequired":"","LoanTenure":"15","Loan_Cost":"","Net_Income":"210000","PAN_No":"Aapad4432g","ProdName":"Home Loan","ProductId":"12","ProfitAfterTax":"","Turnover":"","bank_id":"20"}
     */

    private CustomerApplicationEntity data;

    public CustomerApplicationEntity getData() {
        return data;
    }

    public void setData(CustomerApplicationEntity data) {
        this.data = data;
    }


}
