package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.FollowUpHistoryDetailsEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpHistoryDetailsResponse extends APIResponse {

    private ListFollowUpHistoryDetailsEntity result;

    public ListFollowUpHistoryDetailsEntity getResult() {
        return result;
    }

    public void setResult(ListFollowUpHistoryDetailsEntity result) {
        this.result = result;
    }

    public  class ListFollowUpHistoryDetailsEntity {
        /**
         * Date : 2016-07-06
         * Name : Vandana Karande
         * Remark : 10 lakhs amount in that 6,65,000/- BT and 3,31,000/- amount disbursed
         * Status : Login
         * time :
         */

        private List<FollowUpHistoryDetailsEntity> followupHstryData;

        public List<FollowUpHistoryDetailsEntity> getFollowupHstryData() {
            return followupHstryData;
        }

        public void setFollowupHstryData(List<FollowUpHistoryDetailsEntity> followupHstryData) {
            this.followupHstryData = followupHstryData;
        }


    }
}
