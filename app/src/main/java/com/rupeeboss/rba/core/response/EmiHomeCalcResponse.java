package com.rupeeboss.rba.core.response;

import com.google.gson.annotations.SerializedName;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.EmiHomeCalcuatorEntity;

/**
 * Created by IN-RB on 15-06-2017.
 */

public class EmiHomeCalcResponse extends APIResponse {

    /**
     * status : 1
     * data : {"Bank_Id":20,"Bank_Code":"HDFC","Bank_Name":"HDFC BANK LTD","Product_Id":"9.00","roi":"11.49","loan_eligible":"500000","processingfee":10000,"emi":13042,"foir":"40.00","netincome":"69800","LoanTenure":4,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/hdfc.png","guarantor_required":"No","Women_roi":"11.49","Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"4.000","Part_Pmt_Fixed":null,"Profession":1,"roi_type":"Fixed"}
     * err_code :
     */



    private EmiHomeCalcuatorEntity data;
    private String err_code;


    public EmiHomeCalcuatorEntity getData() {
        return data;
    }

    public void setData(EmiHomeCalcuatorEntity data) {
        this.data = data;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }


}
