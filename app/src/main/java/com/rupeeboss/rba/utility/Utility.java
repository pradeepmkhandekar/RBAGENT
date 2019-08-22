package com.rupeeboss.rba.utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rupeeboss.rba.core.model.AssigneeEntity;
import com.rupeeboss.rba.core.model.BreakDtlsEntity;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.model.StatusEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IN-RB on 01-02-2017.
 */
public class Utility {

    public static List<StatusEntity> liStatus;
    public static List<AssigneeEntity> liAssignee;
    public static List<ProductsEntity> liProduct;

    //  public static String EmpCode = "0";
    public static String EmpName;
    public static String PASSWORD = "password";
    public static String EMPLOYEE_ID = "employeeid";
    public static String REFERRAL_EMPLOYEEID = "referral_employeeid";
    public static String REFERRAL_BROKERID = "referral_brokerid";
    public static String REFERRAL_SOURCE= "referral_source";

    public static int LUNCH = 0;
    public static int OUT = 0;
    public static int TEA = 0;
    public static int PERSONAL = 0;
    public static String DEVICE_ID = "deviceid";
    public static String DEVICE_TOKEN = "devicetoken";
    public static String CALL_STATUS = "callstatus";
    public static String CALL_STATUS_CONTACT = "callstatuscontact";
    public static String IS_FEEDBACK = "isfeedback";
    public static String CALL_STATUS_FOLLOWUP = "callstatusfollowup";
    public static String CONTACT_MANAGER = "contactmanager";
    public static String HOME_EMI_CAL = "homeemicalculator";
    public static String BUISNESS_EMI_CAL = "buisnessemicalculator";
    public static String MY_BUSISNESS= "mybuisness";
    public static String MY_BUSISNESS_HDR= "mybuisnessheader";
    public static String MY_BUSISNESS_type= "mybuisnesstype";
    public static String MY_LIST= "myListLead";

    public static String SEND_MAIL_TO = " wecare@rupeeboss.com";

    public static String startTime = "10:00:00";
    public static String endTime = "18:00:00";
    public static long TIME_INTERVAL = 1800000;//30 Min
    //  public static LocationTracker locationTracker;


    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 124;
    final static public int REQUEST_CODE_ASK_PERMISSIONS_ALL = 125;
    public static final int REQUEST_CODE_ASK_PERMISSIONS_Login= 126;

    /*public static String[] perms = {"android.permission.WRITE_CALL_LOG",
            "android.permission.CALL_PHONE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.RECORD_AUDIO",F
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.READ_CONTACTS",
            "android.permission.SEND_SMS",
            "android.permission.READ_SMS"}; //"android.permission.ACCESS_COARSE_LOCATION",
*/
    public static String getCurrentMobileDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public static void hideKeyBoard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return 0;
    }
//    public static void setEmployeeData(String empCode, String empName) {
//        EmpCode = empCode;
//        EmpName = empName;
//    }



    /*public static List<StatusEntity> setStatusList(List<StatusEntity> listatus) {

        if (liStatus == null) {
            liStatus = new ArrayList<>();
        }

        if (listatus != null) {
            liStatus = listatus;
        }

        return liStatus;
    }

    public static List<AssigneeEntity> setAssigneeList(List<AssigneeEntity> liassignee) {

        if (liAssignee == null) {
            liAssignee = new ArrayList<>();
        }

        if (liAssignee != null) {
            liAssignee = liassignee;
        }

        return liAssignee;
    }

    public static List<ProductsEntity> setProductList(List<ProductsEntity> liproduct) {

        if (liProduct == null) {
            liProduct = new ArrayList<>();
        }

        if (liProduct != null) {
            liProduct = liproduct;
        }

        return liProduct;
    }

    public static void setBreakList(List<BreakDtlsEntity> libreakdetail) {


        if (libreakdetail != null) {
            for (int i = 0; i < libreakdetail.size(); i++) {

                if (libreakdetail.get(i).getBreakKey().matches("2")) {
                    TEA = libreakdetail.get(i).getTime();
                } else if (libreakdetail.get(i).getBreakKey().matches("3")) {
                    LUNCH = libreakdetail.get(i).getTime();
                } else if (libreakdetail.get(i).getBreakKey().matches("4")) {
                    PERSONAL = libreakdetail.get(i).getTime();
                } else if (libreakdetail.get(i).getBreakKey().matches("5")) {
                    OUT = libreakdetail.get(i).getTime();
                }
            }
        }

    }

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return 0;
    }

    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return "0";
    }*/

    public static boolean checkInternetStatus(Context context) {
      /*  ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                conMgr.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                conMgr.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                conMgr.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        }*/
        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            return true;
        }
        return false;
    }

    public static boolean checkGpsStatus(Context context) {
        PackageManager pm = context.getPackageManager();
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean hasGps = pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
        boolean gps_enabled = false;
        if (hasGps) {
            gps_enabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        return gps_enabled;
    }

    public static String getMobileDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }


    public static boolean validateDayTime() {
        String dayOfTheWeek = "";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //String endTime = "22:00:00";
        StringTokenizer startToken = new StringTokenizer(startTime);
        String startT = startToken.nextToken();

        StringTokenizer endToken = new StringTokenizer(endTime);
        String endT = endToken.nextToken();
        //String time = tk.nextToken();

        String currentDate = "";
        String currentT = "";
        try {
            currentDate = dateFormatter.format(Calendar.getInstance().getTime());
            dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", Calendar.getInstance().getTime());
            StringTokenizer currentTokenToken = new StringTokenizer(currentDate);
            String currentD = currentTokenToken.nextToken();
            currentT = currentTokenToken.nextToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date startDate;
        Date endDate;
        try {
            startDate = sdf.parse(startT);
            endDate = sdf.parse(endT);
            Date currentDateToken = sdf.parse(currentT);
            if (!dayOfTheWeek.matches("Sunday")) {
                if (startDate.getTime() <= currentDateToken.getTime() && endDate.getTime() >= currentDateToken.getTime()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getBase64String(String filePath) {
        return Base64.encodeToString(getByteArray(filePath), 0);
    }

    public static byte[] getByteArray(String filePath) {
        File file = new File(filePath);
        byte[] bytes = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static boolean deleteAudioFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }


    public static String convertBitmapToBase64(Bitmap bitmap) {
        try {
            if (bitmap != null) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, os);
                byte[] byteArray = os.toByteArray();
                return Base64.encodeToString(byteArray, Base64.NO_WRAP);
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap convertBase64ToBitmap(String b64) {
        try {
            byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);

            return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
