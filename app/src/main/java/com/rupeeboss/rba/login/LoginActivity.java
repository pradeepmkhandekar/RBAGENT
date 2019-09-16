package com.rupeeboss.rba.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.ReadDeviceID;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.login.LoginController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.LoginResponse;
import com.rupeeboss.rba.forgetpwd.ForgotPasswordActivity;
import com.rupeeboss.rba.home.MainActivity;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

public class LoginActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener , GoogleApiClient.OnConnectionFailedListener{
    int localAppVersionCode, serverAppVersion;
    TextInputEditText etpanno, etPassword;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String deviceId;
    TextView txtnewRegistration;
    TextView txtForgotPwd;
    Button btnLogin;
    ImageView imgGoogleLogin;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.READ_PHONE_STATE",
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION"
         }; //"android.permission.ACCESS_COARSE_LOCATION",


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initialize_widgets();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void initialize_widgets() {
        etpanno = (TextInputEditText) findViewById(R.id.etpanno);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtForgotPwd = (TextView) findViewById(R.id.txtforgotPwd);
        imgGoogleLogin = (ImageView) findViewById(R.id.img_google_login);

        if (!checkPermission()) {
            requestPermission();
        }

        btnLogin.setOnClickListener(this);
        txtnewRegistration = (TextView) findViewById(R.id.txtnewRegistration);
        txtnewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewRegistration.class));
            }
        });
        txtForgotPwd.setOnClickListener(this);
        imgGoogleLogin.setOnClickListener(this);
    }

    private void openAppMarketPlace() {

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    private void showDialog(String msg) {
       /* android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openAppMarketPlace();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();*/

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setCancelable(false);
        // dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView txtMsg = (TextView) dialog.findViewById(R.id.txtMsg);
        txtMsg.setText(msg);
        /*ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);*/
        ConstraintLayout clUpdate = (ConstraintLayout) dialog.findViewById(R.id.clUpdate);
        //TextView btnOk = (TextView) dialog.findViewById(R.id.tvOk);
        // if button is clicked, close the custom dialog
        clUpdate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            dialog.dismiss();
                                            finish();
                                            openAppMarketPlace();
                                        }
                                    }
        );
        dialog.show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_google_login) {
            showProgressDialog();
            signIn();
        }
        else if (v.getId() == R.id.txtforgotPwd) {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        }
       else if (v.getId() == R.id.btnLogin) {
            if (etpanno.getText().toString().matches("")) {
                etpanno.setError("ENTER VALID PANCARD");
                etpanno.requestFocus();
                return;
            }
            if (etPassword.getText().toString().matches("")) {
                etPassword.setError("ENTER CORRECT PASSWORD");
                etPassword.requestFocus();
                return;
            }
            try {
                deviceId = sharedPreferences.getString(Constants.DEVICE_ID, "");
                if (deviceId == null || deviceId.matches("")) {
                    deviceId = new ReadDeviceID(LoginActivity.this).getAndroidID();
                    editor.putString(Constants.DEVICE_ID, deviceId);
                    editor.commit();
                }
                //refreshedToken = sharedPreferences.getString(Constants.DEVICE_TOKEN, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            showProgressDialog();
            new LoginController(this).login(etpanno.getText().toString(), etPassword.getText().toString(), deviceId, "", this);


        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        dismissDialog();
        if (response instanceof LoginResponse) {
            if (response.getStatusId() == 0) {
                new LoginFacade(LoginActivity.this).storeLoginDetails(etpanno.getText().toString(), etPassword.getText().toString(), deviceId);
                localAppVersionCode = Utility.getVersionCode(LoginActivity.this);
                serverAppVersion = Integer.parseInt(new LoginFacade(LoginActivity.this).getUser().getVersionCode());
                Log.d("APP_VERSION", "Local = " + localAppVersionCode + "Market = " + serverAppVersion);
                if (localAppVersionCode < serverAppVersion) {
                    showDialog("Update RBAgent App..");
                    return;
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private boolean checkPermission() {

        int accessCamera = ContextCompat.checkSelfPermission(getApplicationContext(), perms[0]);

        int readPhonestate = ContextCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int recordAudio = ContextCompat.checkSelfPermission(getApplicationContext(), perms[2]);
        int writeExternal = ContextCompat.checkSelfPermission(getApplicationContext(), perms[3]);
        int fineLocation = ContextCompat.checkSelfPermission(getApplicationContext(), perms[4]);
        //int fineLocation = ContextCompat.checkSelfPermission(getApplicationContext(), perms[7]);
        return accessCamera == PackageManager.PERMISSION_GRANTED

                && readPhonestate == PackageManager.PERMISSION_GRANTED
                && recordAudio == PackageManager.PERMISSION_GRANTED
                && writeExternal == PackageManager.PERMISSION_GRANTED

                && fineLocation == PackageManager.PERMISSION_GRANTED;


    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Utility.REQUEST_CODE_ASK_PERMISSIONS_Login);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        dismissDialog();
        Toast.makeText(this,"Connection Failed" + connectionResult,Toast.LENGTH_SHORT).show();
    }

    private void signIn() {
        if (mGoogleApiClient.isConnected())
        {
            signOut();
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(false);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if(requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        dismissDialog();
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Toast.makeText(this,acct.getEmail(),Toast.LENGTH_SHORT).show();

            String fName = acct.getGivenName();
            String email = acct.getEmail();
            String lname = acct.getFamilyName();



        } else {
            // Signed out, show unauthenticated UI.

            Toast.makeText(this,"Connection Failed",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {

            imgGoogleLogin.setVisibility(View.GONE);
        } else {

            imgGoogleLogin.setVisibility(View.VISIBLE);
        }
    }
}
