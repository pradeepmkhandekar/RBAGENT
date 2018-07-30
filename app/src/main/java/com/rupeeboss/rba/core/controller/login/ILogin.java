package com.rupeeboss.rba.core.controller.login;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public interface ILogin {

    void login(String panNo, String password, String devID, String deviceToken, IResponseSubcriber IResponseSubcriber);

    void uploadProfilePicture(String panNo, String base64Image, IResponseSubcriber iResponseSubcriber);
}
