package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.LeadHstryDataEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public class HistoryResponse extends APIResponse {

    /**
     * LeadHstryData : [{"Assignee":"1","Date":"01-01-2016","Name":"Pankaj K","Product":"Home Loan","Remark":"tte","Status":""}]
     * empCode : 10001
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private String empCode;
        /**
         * Assignee : 1
         * Date : 01-01-2016
         * Name : Pankaj K
         * Product : Home Loan
         * Remark : tte
         * Status :
         */

        private List<LeadHstryDataEntity> LeadHstryData;

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public List<LeadHstryDataEntity> getLeadHstryData() {
            return LeadHstryData;
        }

        public void setLeadHstryData(List<LeadHstryDataEntity> LeadHstryData) {
            this.LeadHstryData = LeadHstryData;
        }


    }
}
