package com.rupeeboss.rba.changepassword;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.authenticate.Authentication;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.ChangePasseordResponse;

public class ChangePasswordActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {

//    EditText
    TextInputEditText etoldpswd, etnewpswd, etconfirmpswd;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        etoldpswd = (TextInputEditText) findViewById(R.id.etOldPassword);
        etnewpswd = (TextInputEditText) findViewById(R.id.etNewPassword);
        etconfirmpswd = (TextInputEditText) findViewById(R.id.etConfirmPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSubmit) {

            if (etoldpswd.getText().toString().matches("")) {
                // Snackbar.make(etEmpCode, "Enter valid input", Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, "ENTER OLD PASSWORD", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etnewpswd.getText().toString().matches("")) {
                // Snackbar.make(etEmpCode, "Enter valid input", Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, "ENTER NEW PASSWORD", Toast.LENGTH_SHORT).show();
                return;
            }
            if (etconfirmpswd.getText().toString().matches("")) {
                // Snackbar.make(etEmpCode, "Enter valid input", Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, "RE-ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!etconfirmpswd.getText().toString().matches(etnewpswd.getText().toString())) {
                Toast.makeText(this, "PASSWORD NOT MATCHED", Toast.LENGTH_SHORT).show();
                return;
            }

            showProgressDialog();
            new Authentication(ChangePasswordActivity.this).changePassword(new LoginFacade(ChangePasswordActivity.this).getPanNumber(), etoldpswd.getText().toString(), etnewpswd.getText().toString(), ChangePasswordActivity.this);


        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof ChangePasseordResponse) {
            if (response.getStatusId() == 0) {
                //Snackbar.make(etoldpswd, response.getMessage(), Snackbar.LENGTH_LONG).show();
                Toast.makeText(ChangePasswordActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                clear();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etoldpswd, t.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void clear() {
        etoldpswd.setText("");
        etnewpswd.setText("");
        etconfirmpswd.setText("");
    }
}
