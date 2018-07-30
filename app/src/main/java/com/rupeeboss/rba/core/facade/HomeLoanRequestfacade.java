package com.rupeeboss.rba.core.facade;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.utility.Constants;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class HomeLoanRequestfacade {

    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public HomeLoanRequestfacade(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, mContext.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean storeHomeLoanRequest(HomeLoanRequest homeLoanRequest) {

        Gson gson = new Gson();
        editor.putString(Constants.HOMELOAN_REQUEST_FACADE, gson.toJson(homeLoanRequest));
        return editor.commit();
    }

    public HomeLoanRequest getHomeLoanRequest() {
        Gson gson = new Gson();
        return gson.fromJson(sharedPreferences.getString(Constants.HOMELOAN_REQUEST_FACADE, ""), HomeLoanRequest.class);
    }

    public boolean clearCache() {
        editor.remove(Constants.HOMELOAN_REQUEST_FACADE);
        return editor.commit();
    }

}
