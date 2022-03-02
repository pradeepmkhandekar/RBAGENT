package com.rupeeboss.rba.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.textfield.TextInputEditText;
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
import com.rupeeboss.rba.utility.PrefManager;
import com.rupeeboss.rba.utility.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {
    int localAppVersionCode, serverAppVersion;
    TextInputEditText etpanno, etPassword;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String deviceId;
    TextView txtnewRegistration;
    TextView txtForgotPwd;
    Button btnLogin;
    ImageView imgGoogleLogin,imgfaceebook;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    private CallbackManager callbackManager;
    AccessTokenTracker tokenTracker;


    PrefManager prefManager;
    LoginButton loginButton;



    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        prefManager = new PrefManager(this);
        initialize_widgets();


        // region FaceBook Integration step 1

        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        // FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        tokenTracker  = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                if(currentAccessToken == null)
                {
                   //"User Logged Out

                }else {
                    loadUserProfile(currentAccessToken);
                    LoginManager.getInstance().logOut();   // for logout for facebook After getting login
                }

            }
        };

        tokenTracker.startTracking();

        //endregion

    }

    // region FaceBook Integration step 2

    private void loadUserProfile (AccessToken newaccessToken){

        GraphRequest request = GraphRequest.newMeRequest(newaccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
//                    String first_name = object.getString("first_name");
//                    String last_name = object.getString("last_name");
//                    String id = object.getString("id");


                    String email = object.getString("email");
                    //email ="kumaranchal788@gmail.com";
                    new LoginController(LoginActivity.this).login(email, "", deviceId, "", "Y", LoginActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

     // endregion

    private void initialize_widgets() {
        etpanno = (TextInputEditText) findViewById(R.id.etpanno);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtForgotPwd = (TextView) findViewById(R.id.txtforgotPwd);
        imgGoogleLogin = (ImageView) findViewById(R.id.img_google_login);
        imgfaceebook = (ImageView) findViewById(R.id.img_faceebook);
        loginButton = (LoginButton) findViewById(R.id.loginButton);

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
        imgfaceebook.setOnClickListener(this);
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

      //  Log.d("TOKEN", "Token :" + prefManager.getToken());
        if (v.getId() == R.id.img_google_login) {
            Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, true, null, null, null, null);
            startActivityForResult(intent, 000);
            // showProgressDialog();
            //  signIn();
        } else if (v.getId() == R.id.txtforgotPwd) {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        }else if(v.getId() == R.id.img_faceebook){

            loginButton.performClick();

        } else if (v.getId() == R.id.btnLogin) {
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
            new LoginController(this).login(etpanno.getText().toString(), etPassword.getText().toString(), deviceId, "", "N", this);

            //    new LoginController(this).login("kumaranchal788@gmail.com", "", deviceId, "","Y", this);

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

                if (!prefManager.getSharePushType().equals("")) {

                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra(Utility.PUSH_LOGIN_PAGE, "555"));
                    finish();
                } else {

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }






    private boolean checkPermission() {

        int accessCamera = ContextCompat.checkSelfPermission(getApplicationContext(), perms[0]);
        int writeExternal = ContextCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int readExternal = ContextCompat.checkSelfPermission(getApplicationContext(), perms[2]);

        return accessCamera == PackageManager.PERMISSION_GRANTED

                && writeExternal == PackageManager.PERMISSION_GRANTED

                && readExternal == PackageManager.PERMISSION_GRANTED;


    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Utility.REQUEST_CODE_ASK_PERMISSIONS_Login);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            LoginManager.getInstance().logOut();
        }catch (Exception ex)
        {

        }
    }

    // region facebook Hashkey
    // Todo : genertate facebook Hashkey (put buildvariant in release mode) we have to put this key in facebook developer that app console.
    // for Debug Mode : h5+/D2GcTgYTKSX/ikIR/jdVlUU=
    // for Release Mode :  Gi/hz4epMMnUrMSLxl6pClYlXqM=


    private void getReleaseKey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));


            }
        } catch (PackageManager.NameNotFoundException ex) {
            Log.d("KeyHash", ex.toString());
        } catch (NoSuchAlgorithmException ex) {
            Log.d("KeyHash", ex.toString());
        }
    }

    //endregion
}
