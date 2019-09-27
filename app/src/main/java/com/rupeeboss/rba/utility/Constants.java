package com.rupeeboss.rba.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class Constants {

    public static String SHAREDPREFERENCE_TITLE = "RBA_android";
    public static String CITY_FACADE = "citylist";
    public static String HOMELOAN_REQUEST_FACADE = "homeloanrequest";
    public static String PRODUCT_FACADE = "productlist";
    public static String PROPERTY_FACADE = "propertylist";
    public static String LOGIN_FACADE = "logindata";
    public static String PROFILE_URL = "profileurl";
    public static String QUOTES = "quotes";
    public static String DEVICE_ID = "deviceid";
    public static String DEVICE_TOKEN = "devicetoken";
    public static String PAN_NUMBER = "pannumber";
    public static String PASSWORD = "password";
    public static String WEB_URL = "WEBURL";
    public static String PERSONAL_LOAN_QUOTES = "personalloanquotes";
    public static String HL_REQUEST = "homeloanRequest";
    public static String PL_REQUEST = "personalloanRequest";
    public static String LAP_REQUEST = "loanagainstpropertyRequest";

    public static final int PERMISSION_CALLBACK_CONSTANT = 100;
    public static final int REQUEST_PERMISSION_SETTING = 101;
    public static final int PERMISSION_CAMERA_STORACGE_CONSTANT = 103;
    public static final int PERMISSION_CALLBACK_SUPPORT = 104;

    public static void hideKeyBoard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
