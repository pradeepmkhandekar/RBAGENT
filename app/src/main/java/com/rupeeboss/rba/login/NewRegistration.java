package com.rupeeboss.rba.login;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.NewRegistration.NewRegistrationController;
import com.rupeeboss.rba.core.facade.CityFacade;
import com.rupeeboss.rba.core.model.CityEntity;
import com.rupeeboss.rba.core.request.requestentity.RegisterRequestEntity;
import com.rupeeboss.rba.core.response.NewRegistrationResponse;
import com.rupeeboss.rba.home.MainActivity;
import com.rupeeboss.rba.referrar.InstallReferrerReceiver;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class NewRegistration extends BaseActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener, IResponseSubcriber {

    EditText etFName, etLName, etEmail, etMobile, etUserPassword, etPAN_No, etRePassword, etReferEmployeeCode;
    Spinner spCity;
    Button btnSubmit ;
    ImageView  imgGoogleLogin  ;
    ArrayList<String> arraycity;
    ArrayAdapter<String> cityAdapter;
    TextView txtnewRegistration, txtReferEmployee;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;

    String parentBrokerId, parentEmpCode, source = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        arraycity = new ArrayList<String>();
        initialize();
        loadSpinner();

        ExtractReferralCode();
         Constants.hideKeyBoard(etFName,NewRegistration.this);
    }

    private void ExtractReferralCode() {

        Log.d("InstallReferrerReceiver", InstallReferrerReceiver.REF_EMPLOYEEID
                + InstallReferrerReceiver.REF_BROKERID
                + InstallReferrerReceiver.REF_SOURCE);
        parentBrokerId = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE)
                .getString(Utility.REFERRAL_BROKERID, "");
        parentEmpCode = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE)
                .getString(Utility.REFERRAL_EMPLOYEEID, "");

        source = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE)
                .getString(Utility.REFERRAL_SOURCE, "");
        //source="YQBwAHAA";
       // parentEmpCode="MQAwADAAOQAzADgA";


     //   etReferEmployeeCode.setText(""+Base64.decode(parentEmpCode,Base64()).toString());
        if (source.equals("")) {

            etReferEmployeeCode.setEnabled(true);
        } else {

            try {
                byte[] data1 = Base64.decode(parentEmpCode, Base64.DEFAULT);
                String text1 = null;
                text1 = new String(data1, "UTF-8");
                etReferEmployeeCode.setText(text1);
                etReferEmployeeCode.setEnabled(false);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    private void initialize() {
        spCity = (Spinner) findViewById(R.id.sp_city);
        etFName = (EditText) findViewById(R.id.et_first_name);
        etLName = (EditText) findViewById(R.id.et_last_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etUserPassword = (EditText) findViewById(R.id.et_password);
        etPAN_No = (EditText) findViewById(R.id.et_pan_no);
        etReferEmployeeCode = (EditText) findViewById(R.id.etReferEmployeeCode);
        etRePassword = (EditText) findViewById(R.id.etRePassword);
        etPAN_No.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        txtReferEmployee = (TextView) findViewById(R.id.txtReferEmployee);
        imgGoogleLogin = (ImageView) findViewById(R.id.img_google_login);

        imgGoogleLogin.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void loadSpinner() {
        cityAdapter = new ArrayAdapter<String>(NewRegistration.this,
                android.R.layout.simple_spinner_item, getCityList());
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(cityAdapter);
    }

    private ArrayList<String> getCityList() {
        if (new CityFacade(NewRegistration.this).getCityList() != null) {
            for (CityEntity entity : new CityFacade(NewRegistration.this).getCityList()) {
                arraycity.add(entity.getCity_Name());
            }
        }

        return arraycity;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.img_google_login) {
            showProgressDialog();
            signIn();
        }
        else if (view.getId() == R.id.btn_submit) {
            Utility.hideKeyBoard(view, NewRegistration.this);


            String email = etEmail.getText().toString();
            String password = etUserPassword.getText().toString();
            String FName = etFName.getText().toString();
            String LName = etLName.getText().toString();
            String mobileNo = etMobile.getText().toString();
            String Pan_no = etPAN_No.getText().toString().toUpperCase();

            if (TextUtils.isEmpty(FName)) {
                etFName.setError("ENTER FIRST NAME");
                etFName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(LName)) {
                etLName.setError("ENTER LAST NAME");
                etLName.requestFocus();
                return;
            }

            if (!validateEmailAddress(etEmail)) {
                etEmail.setError("ENTER VALID EMAIL");
                etEmail.requestFocus();
                return;
            }
            if (!validatePhoneNumber(etMobile)) {
                etMobile.setError("ENTER VALID MOBILE");
                etMobile.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(Pan_no)) {
                etPAN_No.setError("ENTER PAN NO");
                etPAN_No.requestFocus();
                return;
            }
            if (!isValidPan(Pan_no)) {
                etPAN_No.setError("ENTER VALID PAN NO");
                etPAN_No.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                etUserPassword.setError("ENTER PASSWORD");
                etUserPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                etUserPassword.setError("MINIMUM 6 CHARACTER REQUIRED");
                etUserPassword.requestFocus();
                return;
            }
            if (!etRePassword.getText().toString().matches(etUserPassword.getText().toString())) {
                etRePassword.setError("RE-ENTER PASWWORD");
                etRePassword.requestFocus();
                return;
            }


            int cityID = new CityFacade(NewRegistration.this).getCityId(spCity.getSelectedItem().toString());

            RegisterRequestEntity objregister = new RegisterRequestEntity();
            objregister.setFirst_Name(FName);
            objregister.setLast_Name(LName);
            objregister.setCity(Integer.toString(cityID));
            objregister.setContact_No(etMobile.getText().toString());
            objregister.setEmail_Id(etEmail.getText().toString());
            objregister.setPAN_No(etPAN_No.getText().toString());
            objregister.setParentBrokerId(parentBrokerId);
            objregister.setParentEmpCode(parentEmpCode);
            objregister.setSource(source);
            objregister.setUserPassword(etUserPassword.getText().toString());
            if (source.equals("")) {
                objregister.setNewEmpCode(etReferEmployeeCode.getText().toString());
            }else
            {
                objregister.setNewEmpCode("");
            }

            showProgressDialog();
            new NewRegistrationController(NewRegistration.this).Register(objregister, this);
        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof NewRegistrationResponse) {
            if (response.getStatus_Id() == 0) {
                etEmail.setText("");
                etMobile.setText("");
                etLName.setText("");
                etFName.setText("");
                etPAN_No.setText("");
                etUserPassword.setText("");
                //startActivity(new Intent(NewRegistration.this, LoginActivity.class));
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
            Snackbar.make(etFName, response.getMessage(), Snackbar.LENGTH_SHORT).show();

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etFName, t.getMessage(), Snackbar.LENGTH_SHORT).show();
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

            String fName = acct.getGivenName();
            String email = acct.getEmail();
            String lname = acct.getFamilyName();

            etFName.setText(fName);
            etLName.setText(lname);
            etEmail.setText(email);

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
