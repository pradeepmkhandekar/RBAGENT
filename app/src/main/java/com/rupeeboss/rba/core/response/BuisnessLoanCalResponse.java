package com.rupeeboss.rba.core.response;

import com.google.gson.annotations.SerializedName;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.BuisnessLoanCalEntity;

/**
 * Created by IN-RB on 05-07-2017.
 */

public class BuisnessLoanCalResponse extends APIResponse {



    /**
     * status : 1
     * data : {"Bank_Id":113,"Bank_Name":"RB [Advantage]","Bank_Code":"RB ADVANTAGE","Product_Id":"13.00","roi":"15.50","foir":"200.00","loan_eligible":"14322544","emi":500010,"netincome":250000,"LoanTenure":3,"pf_type":"Percentage","pf":"2.00","processingfee":10000.2,"LoanRequired":"20000000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/ADVANTAGE_logo.png","guarantor_required":"No","Women_roi":"15.50","Lock_In_Period":"6.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"0.000","Part_Pmt_Fixed":"0.00","Profession":2,"roi_type":"Floating"}
     * err_code :
     */


    private BuisnessLoanCalEntity data;
    private String err_code;


    public BuisnessLoanCalEntity getData() {
        return data;
    }

    public void setData(BuisnessLoanCalEntity data) {
        this.data = data;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }


}
