package com.rupeeboss.rba.core.controller.history;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public interface IHistory {
    void getHistory(String empID, IResponseSubcriber iResponseSubcribe);
}
