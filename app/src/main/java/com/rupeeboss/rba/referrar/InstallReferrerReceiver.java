package com.rupeeboss.rba.referrar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.UrlQuerySanitizer;
import android.util.Log;
import android.widget.Toast;

import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

public class InstallReferrerReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static String REF_EMPLOYEEID, REF_BROKERID, REF_SOURCE = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");
        Log.d("InstallReferrerReceiver", referrer);
        //Use the referrer
        sharedPreferences = context.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String[] splitArray = referrer.split("@");

        if (splitArray.length >= 1) {
            if (splitArray[0] != null) {
                Log.d("REFERRAL_EMPLOYEEID", splitArray[0]);
                REF_EMPLOYEEID = splitArray[0];
                editor.putString(Utility.REFERRAL_EMPLOYEEID, splitArray[0]);
            }
        }
        if (splitArray.length >= 2) {
            if (splitArray[1] != null) {
                Log.d("REFERRAL_BROKERID", splitArray[1]);
                REF_BROKERID = splitArray[1];
                editor.putString(Utility.REFERRAL_BROKERID, splitArray[1]);
            }
        }
        if (splitArray.length >= 3) {
            if (splitArray[2] != null) {
                REF_SOURCE = splitArray[2];
                Log.d("REFERRAL_SOURCE", splitArray[2]);
                editor.putString(Utility.REFERRAL_SOURCE, splitArray[2]);
            }
        }
        editor.commit();
    }
}