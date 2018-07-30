package com.rupeeboss.rba.core.response;

import com.google.gson.annotations.SerializedName;
import com.rupeeboss.rba.core.APIResponse;

/**
 * Created by Rajeev Ranjan on 29/06/2017.
 */

public class WorkCapitalResponse extends APIResponse {
    /**
     * status : 0
     * data : {"proposedlimit":0}
     * err_code :
     */

    private DataBean data;
    private String err_code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public static class DataBean {
        /**
         * proposedlimit : 0
         */

        private double proposedlimit;

        public double getProposedlimit() {
            return proposedlimit;
        }

        public void setProposedlimit(double proposedlimit) {
            this.proposedlimit = proposedlimit;
        }
    }
}
