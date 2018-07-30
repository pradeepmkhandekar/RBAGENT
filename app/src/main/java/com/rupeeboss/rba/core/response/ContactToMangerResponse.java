package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.ContactMangEntity;

/**
 * Created by IN-RB on 22-06-2017.
 */

public class ContactToMangerResponse extends APIResponse {


    /**
     * result : {"reportingEmpCompanyId":"1000","reportingEmpDesignation":"Manager","reportingEmpEmail":"mahendra.thakare@rupeeboss.com","reportingEmpMobile":"9920638907","reportingEmpName":"Mahendra Thakare"}
     */

    private ContactMangEntity result;

    public ContactMangEntity getResult() {
        return result;
    }

    public void setResult(ContactMangEntity result) {
        this.result = result;
    }


}
