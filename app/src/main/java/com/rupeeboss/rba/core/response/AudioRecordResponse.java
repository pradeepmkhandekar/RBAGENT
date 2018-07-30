package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;

/**
 * Created by Rajeev Ranjan on 06/01/2017.
 */

public class AudioRecordResponse extends APIResponse {

    private String localdb_id;

    public String getLocaldb_id() {
        return localdb_id;
    }

    public void setLocaldb_id(String localdb_id) {
        this.localdb_id = localdb_id;
    }
}
