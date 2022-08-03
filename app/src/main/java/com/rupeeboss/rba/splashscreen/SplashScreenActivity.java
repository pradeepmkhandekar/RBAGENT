package com.rupeeboss.rba.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


//import com.crashlytics.android.Crashlytics;
import com.orm.SugarContext;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.controller.sync.SyncController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core_loan_fm.controller.erploan.ErpLoanController;
import com.rupeeboss.rba.home.MainActivity;
import com.rupeeboss.rba.loan_fm.LoanCityFacade;
import com.rupeeboss.rba.login.LoginActivity;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
//import io.fabric.sdk.android.Fabric;

public class SplashScreenActivity extends BaseActivity {
    private static final String TAG = "SplashScreenActivity";
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    LoginFacade loginFacade;

    // variable for install referer client.
    InstallReferrerClient referrerClient;
    private TextView refrerTV;

    // creating an empty string for our referer.
    String refrer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Fabric.with(this, new Crashlytics());
        loginFacade = new LoginFacade(this);
        // TODO: Move this to where you establish a user session
        logUser();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        refrerTV = findViewById(R.id.idTVRefrer);

        // on below line we are building our install referrer client and building it.
        referrerClient = InstallReferrerClient.newBuilder(this).build();

        // on below line we are starting its connection.
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                // this method is called when install referer setup is finished.
                switch (responseCode) {
                    // we are using switch case to check the response.
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        // this case is called when the status is OK and
                        ReferrerDetails response = null;
                        try {
                            // on below line we are getting referrer details
                            // by calling get install referrer.
                            response = referrerClient.getInstallReferrer();

                            // on below line we are getting referrer url.
                            String referrerUrl = response.getInstallReferrer();

                            // on below line we are getting referrer click time.
                            long referrerClickTime = response.getReferrerClickTimestampSeconds();

                            // on below line we are getting app install time
                            long appInstallTime = response.getInstallBeginTimestampSeconds();

                            // on below line we are getting our time when
                            // user has used our apps instant experience.
                            boolean instantExperienceLaunched = response.getGooglePlayInstantParam();

                            // on below line we are getting our
                            // apps install referrer.
                            refrer = response.getInstallReferrer();

                            // on below line we are setting all detail to our text view.
                            refrerTV.setText("Referrer is : \n" + referrerUrl + "\n" + "Referrer Click Time is : " + referrerClickTime + "\nApp Install Time : " + appInstallTime);
                        } catch (RemoteException e) {
                            // handling error case.
                            e.printStackTrace();
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        Toast.makeText(SplashScreenActivity.this, "Feature not supported..", Toast.LENGTH_SHORT).show();
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        Toast.makeText(SplashScreenActivity.this, "Fail to establish connection", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                Toast.makeText(SplashScreenActivity.this, "Service disconnected..", Toast.LENGTH_SHORT).show();
            }
        });




        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE);

        String REFERRAL_EMPLOYEEID = sharedPreferences.getString(Utility.REFERRAL_EMPLOYEEID, "");
        String REFERRAL_BROKERID = sharedPreferences.getString(Utility.REFERRAL_BROKERID, "");
        String REFERRAL_SOURCE = sharedPreferences.getString(Utility.REFERRAL_SOURCE, "");


        Log.d("Splash", REFERRAL_EMPLOYEEID + REFERRAL_BROKERID + REFERRAL_SOURCE);


      /*  Intent i = new Intent("com.android.vending.INSTALL_REFERRER");
        i.setPackage("com.rupeeboss.rba");
//referrer is a composition of the parameter of the campaing
        i.putExtra("referrer", "SDASDASD@122@eweeew");
        sendBroadcast(i);*/
//Loan City
        if(new LoanCityFacade(this) !=null)
        {
            if(new LoanCityFacade(this).getLoanCity()==null)
            {
                new ErpLoanController(this).getcityloan(null);
            }
        }

        new SyncController(SplashScreenActivity.this).getCity();
        SugarContext.init(SplashScreenActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (loginFacade.getUser() != null) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods


//        if (loginFacade.getUser() != null) {
//            Crashlytics.setUserIdentifier(loginFacade.getDeviceId());
//            Crashlytics.setUserEmail(loginFacade.getPanNumber());
//            Crashlytics.setUserName(loginFacade.getUser().getUName());
//        }

    }

//    private void subscribeToPushService() {
//        FirebaseMessaging.getInstance().subscribeToTopic("news");
//
//        Log.d("AndroidBash", "Subscribed");
//        Toast.makeText(MainActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();
//
//    }

}
