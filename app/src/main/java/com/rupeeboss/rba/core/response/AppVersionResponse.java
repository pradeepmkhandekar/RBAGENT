package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;

/**
 * Created by IN-RB on 25-09-2017.
 */

public class AppVersionResponse extends APIResponse {

    /**
     * version_code : 28
     */

    private String version_code;

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }
}
