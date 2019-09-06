package com.rupeeboss.rba.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
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

import io.fabric.sdk.android.Fabric;

public class SplashScreenActivity extends BaseActivity {
    private static final String TAG = "SplashScreenActivity";
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    LoginFacade loginFacade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        loginFacade = new LoginFacade(this);
        // TODO: Move this to where you establish a user session
        logUser();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

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


        if (loginFacade.getUser() != null) {
            Crashlytics.setUserIdentifier(loginFacade.getDeviceId());
            Crashlytics.setUserEmail(loginFacade.getPanNumber());
            Crashlytics.setUserName(loginFacade.getUser().getUName());
        }

    }

//    private void subscribeToPushService() {
//        FirebaseMessaging.getInstance().subscribeToTopic("news");
//
//        Log.d("AndroidBash", "Subscribed");
//        Toast.makeText(MainActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();
//
//    }

}
