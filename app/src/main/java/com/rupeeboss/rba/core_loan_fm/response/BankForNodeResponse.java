package com.rupeeboss.rba.core_loan_fm.response;

import com.rupeeboss.rba.core_loan_fm.APIResponseFM;

import java.util.List;



/**
 * Created by IN-RB on 19-02-2018.
 */

public class BankForNodeResponse extends APIResponseFM {
    private List<MasterData> MasterData;

    public List<MasterData> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterData> MasterData) {
        this.MasterData = MasterData;
    }

    public  class MasterData {
        /**
         * SavedStatus : 0
         * Message : Saved Successfully
         * LoanRequestID : 8
         */

        private int SavedStatus;

        private String Message;
        private int LoanRequestID;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageFm() {
            return Message;
        }

        public void setMessageFm(String MessageX) {
            this.Message = MessageX;
        }

        public int getLoanRequestID() {
            return LoanRequestID;
        }

        public void setLoanRequestID(int LoanRequestID) {
            this.LoanRequestID = LoanRequestID;
        }
    }


}
