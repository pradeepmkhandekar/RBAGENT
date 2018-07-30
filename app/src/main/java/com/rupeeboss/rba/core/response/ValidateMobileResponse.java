package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;

/**
 * Created by Nilesh Birhade on 01-02-2017.
 */

public class ValidateMobileResponse extends APIResponse {


    /**
     * result : {"LeadId":203240,"Name":"Piyush Jaiswal"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public  class ResultBean {
        /**
         * LeadId : 203240
         * Name : Piyush Jaiswal
         */

        private int LeadId;
        private String Name;

        public int getLeadId() {
            return LeadId;
        }

        public void setLeadId(int LeadId) {
            this.LeadId = LeadId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
