package com.rupeeboss.rba.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rupeeboss.rba.core.model.NotifyEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PrefManager {
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name


    public static final String PREF_NAME = "rupeeboss-rbagent";
    public static String PUSH_VERIFY_LOGIN = "push_verify_login";
    public static String NOTIFICATION_COUNTER = "Notification_Counter";
    public static String SHARED_KEY_PUSH_NOTIFY = "shared_notifyFlag";
    public static String SHARED_KEY_PUSH_WEB_URL = "shared_notify_webUrl";
    public static String SHARED_KEY_PUSH_WEB_TITLE = "shared_notify_webTitle";
    private static final String IS_DEVICE_TOKEN = "devicetoken";

    public static String SHARED_KEY_PUSH_Body = "shared_notify_Title";
    public static String SHARED_KEY_PUSH__TITLE = "shared_notify_Body";
    public static String PUSH_NOTIFICATION = "push_notifyication_data";





    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }








    public void clearNotification() {
        pref.edit().remove(SHARED_KEY_PUSH_NOTIFY)
                .remove(PUSH_NOTIFICATION).commit();

    }


    public void setToken(String token) {

        editor.putString(IS_DEVICE_TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(IS_DEVICE_TOKEN, "");
    }

    public int getNotificationCounter() {
        return pref.getInt(NOTIFICATION_COUNTER, 0);
    }

    public void setNotificationCounter(int counter) {
        editor.putInt(NOTIFICATION_COUNTER, counter);
        editor.commit();
    }


    public void setIsUserLogin(boolean isUserLogin) {
        editor.putBoolean(PUSH_VERIFY_LOGIN, isUserLogin);
        editor.commit();
    }

    public boolean getIsUserLogin() {
        return pref.getBoolean(PUSH_VERIFY_LOGIN, false);
    }

    //region Notification

    public void setSharePushType(String type) {

        editor.putString(SHARED_KEY_PUSH_NOTIFY, type);
        editor.commit();
    }

    public String getSharePushType() {
        return pref.getString(SHARED_KEY_PUSH_NOTIFY, "");
    }



    public boolean setPushNotifyPreference(NotifyEntity notifyEntity) {
        try {
            Gson gson = new Gson();
            editor.putString(PUSH_NOTIFICATION, gson.toJson(notifyEntity));
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public NotifyEntity getPushNotifyPreference() {
        String push_KEY = pref.getString(PUSH_NOTIFICATION, "");
        Gson gson = new Gson();
        NotifyEntity notifyEntity = gson.fromJson(push_KEY, NotifyEntity.class);
        if (notifyEntity != null)
            return notifyEntity;
        else
            return null;
    }


    //endregion



}