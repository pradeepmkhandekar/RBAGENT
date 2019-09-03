package com.rupeeboss.rba.core.managecalllog;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class ManageCallLog {

    /*ManageCallLog manageCallLog = new ManageCallLog();
    List<CallDetails> callDetailsList = manageCallLog.getCallLogList(getApplicationContext(), CallLog.Calls.OUTGOING_TYPE, "DESC");
    manageCallLog.deleteNumberFromCallLog(getApplicationContext(), callDetailsList.get(0).getNumber());
    */

    public void deleteOldCallLog(Context context, int selection, String sortOrder) {
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, CallLog.Calls.TYPE + "=" + selection, null, CallLog.Calls.DATE + " " + sortOrder);
        int i = 0;
        Log.d("Cursor Count", "" + cursor.getCount());
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        for (i = 0; i < 5; i++) {
            cursor.moveToNext();
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String callName = cursor.getString(name);
            context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, CallLog.Calls.NUMBER + " = ?", new String[]{phNumber});
            Log.d("CallLog", phNumber + "  " + CallLog.Calls.NUMBER);
        }
        cursor.close();

    }

    public String getRecentOutgoingCall(Context context) {
        return CallLog.Calls.getLastOutgoingCall(context);
    }


    public List<CallDetails> getCallLogList(Context context, int selection, String sortOrder) {
        List<CallDetails> callDetailsList = new ArrayList<CallDetails>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, CallLog.Calls.TYPE + "=" + selection, null, CallLog.Calls.DATE + " " + sortOrder);
        Cursor cursor1 = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                CallLog.Calls.TYPE + "=" + CallLog.Calls.OUTGOING_TYPE,
                null, CallLog.Calls.DATE);
        int i = 0;
        Log.d("Cursor Count", "" + cursor.getCount());
        Log.d("Cursor1 Count", "" + cursor1.getCount());
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);

        for (i = 0; i < 5; i++) {
            cursor.moveToNext();
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String callName = cursor.getString(name);
            callDetailsList.add(new CallDetails(phNumber, callDuration, callType, "" + callDayTime, callName));
        }
            /*while (cursor.moveToNext()) {
                String phNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date(Long.valueOf(callDate));
                String callDuration = cursor.getString(duration);
                String callName = cursor.getString(name);

                callDetailsList.add(new CallDetails(phNumber, callDuration, callType, "" + callDayTime, callName));

            }*/
        cursor.close();
        return callDetailsList;
    }

    public void deleteNumberFromCallLog(Context context, String strNum) {
        try{
            String topCallLog = CallLog.Calls.getLastOutgoingCall(context);
            //if (strNum.matches(topCallLog))
            if (strNum.contains(topCallLog))
                context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, CallLog.Calls.NUMBER + " = ?", new String[]{strNum});
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
