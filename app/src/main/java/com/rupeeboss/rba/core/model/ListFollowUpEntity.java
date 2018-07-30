package com.rupeeboss.rba.core.model;

import java.util.List;

public  class ListFollowUpEntity {
        /**
         * Name : rupeeboss
         * Remark :
         * Status : Future Requirement
         */

        private List<FollowUpEntity> lstFollowupLeads;

        public List<FollowUpEntity> getLstFollowupLeads() {
            return lstFollowupLeads;
        }

        public void setLstFollowupLeads(List<FollowUpEntity> lstFollowupLeads) {
            this.lstFollowupLeads = lstFollowupLeads;
        }


    }