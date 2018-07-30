package com.rupeeboss.rba.core.controller.feedback;

import com.rupeeboss.rba.core.IResponseSubcriber;

/**
 * Created by IN-RB on 02-02-2017.
 */
public interface IFeedBack {
    /*
* "empcode": "10001",
		"Name": "sdfsdf",
		"status": 1,
		"remark": "test",
		"assignee": 1,
		"product": 1,
		"date":"01/01/2017"*/

    void sendFeedback(String empID, int leadId, String name, int status, String remark, int assignee, int product, String date, String time, int demoGiven, String ExpctDisbsDate, IResponseSubcriber iResponseSubcriber);

    void getFeedback(String mobNo, IResponseSubcriber iResponseSubcriber);
}

