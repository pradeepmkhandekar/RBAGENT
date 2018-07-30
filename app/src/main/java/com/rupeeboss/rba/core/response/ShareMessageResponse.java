package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.LstShareMessageEntity;

import java.util.List;

/**
 * Created by IN-RB on 02-02-2017.
 */

public class ShareMessageResponse extends APIResponse {


    /**
     * result : {"lstMsgLnkDtls":[{"MsgBody":"test Msg","link":"rupeeboss.com/bt.php?empCode=","title":"test Msg"},{"MsgBody":"This is a test Message","link":"rupeeboss.com/mt.php?empCode=","title":"Msg"}]}
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private List<LstShareMessageEntity> lstMsgLnkDtls;

        public List<LstShareMessageEntity> getLstMsgLnkDtls() {
            return lstMsgLnkDtls;
        }

        public void setLstMsgLnkDtls(List<LstShareMessageEntity> lstMsgLnkDtls) {
            this.lstMsgLnkDtls = lstMsgLnkDtls;
        }


    }
}
