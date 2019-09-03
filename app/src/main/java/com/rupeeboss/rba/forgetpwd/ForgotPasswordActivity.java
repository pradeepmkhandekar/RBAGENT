package com.rupeeboss.rba.forgetpwd;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.authenticate.Authentication;
import com.rupeeboss.rba.core.response.ForgotPasswordResponse;
import com.rupeeboss.rba.utility.Constants;

public class ForgotPasswordActivity extends BaseActivity implements  IResponseSubcriber,  View.OnClickListener {

    EditText etEmpCode;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etEmpCode = (EditText) findViewById(R.id.etEmpCode);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            Constants.hideKeyBoard(etEmpCode,ForgotPasswordActivity.this);
            if (etEmpCode.getText().toString().matches("")) {
                Snackbar.make(etEmpCode, "Enter valid input", Snackbar.LENGTH_LONG).show();
                return;
            }

            showProgressDialog();
            new Authentication(ForgotPasswordActivity.this).forgotPassword(etEmpCode.getText().toString(),this);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        dismissDialog();
        Constants.hideKeyBoard(etEmpCode, ForgotPasswordActivity.this);
        if (response instanceof ForgotPasswordResponse) {
            if (response.getStatusId() == 0) {

                Toast.makeText(this, "Password sent on your registered email ID/mobile",Toast.LENGTH_LONG).show();
                finish();
            } else {
                Snackbar.make(etEmpCode, response.getMessage(), Snackbar.LENGTH_LONG).show();

            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etEmpCode, t.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
