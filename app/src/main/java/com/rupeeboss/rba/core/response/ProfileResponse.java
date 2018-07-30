package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;

/**
 * Created by IN-RB on 09-03-2017.
 */

public class ProfileResponse extends APIResponse {


    /**
     * profilePic : http://beta.services.rupeeboss.com//Files/Uploads/ProfilePic/rb40000401_20170309_042609_21.Jpeg
     */

    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
