package com.rupeeboss.rba.core;

/**
 * Created by Nilesh Birhade on 17-10-2016.
 */

public interface IResponseSubcriber {

    void OnSuccess(APIResponse response, String message) throws InterruptedException;

    void OnFailure(Throwable t);
}
