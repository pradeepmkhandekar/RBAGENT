package com.rupeeboss.rba.core.controller.sharemsg;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 02-02-2017.
 */

public interface IShareMessage {

    void getShareMessage(String empCode, String brokerId, IResponseSubcriber iResponseSubcribe);
}
