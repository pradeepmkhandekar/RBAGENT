package com.rupeeboss.rba.core.controller.mylist;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 23/08/2017.
 */

public interface IMyList {
    void getParentList(String empCode, int brokerId, IResponseSubcriber iResponseSubcriber);
}
