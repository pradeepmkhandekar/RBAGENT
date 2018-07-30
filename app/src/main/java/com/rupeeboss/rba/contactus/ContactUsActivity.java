package com.rupeeboss.rba.contactus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.contactmanager.ContactMangController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.ContactMangEntity;
import com.rupeeboss.rba.utility.Utility;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etSubject, etBody;
    Button btnSend;
    // ContactMangEntity contactMangEntity;
    String strmsgBody = "";
    LoginFacade loginFacade;
    LinearLayout lvCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loginFacade = new LoginFacade(this);

        initilize();
    }

    private void initilize() {
        etSubject = (EditText) findViewById(R.id.etSubject);
        etBody = (EditText) findViewById(R.id.etBody);
        btnSend = (Button) findViewById(R.id.btnSend);
        lvCall = (LinearLayout) findViewById(R.id.lvCall);

        btnSend.setOnClickListener(this);
        lvCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSend) {

            sendMail();
        } else if (view.getId() == R.id.lvCall) {
            getCalled();
        }
    }

    private void getCalled() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if( getString(R.string.contact_number)!="") {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + getString(R.string.contact_number)));
            startActivity(intent);
        }else {
            Snackbar.make(etBody,"Mobile no. not available", Snackbar.LENGTH_SHORT).show();
        }
    }
    private void sendMail() {

        if (TextUtils.isEmpty(etSubject.getText().toString())) {
            etSubject.setError("ENTER SUBJECT");
            etSubject.requestFocus();
            return;
        } else if (TextUtils.isEmpty(etBody.getText().toString())) {
            etBody.setError("ENTER BODY");
            etBody.requestFocus();
            return;
        }
        strmsgBody = "RBA name: " + loginFacade.getUser().getUName()
                + "\n" + "RBA pan number: " + loginFacade.getPanNumber().toUpperCase() + "\n"
                + "Message: " + etBody.getText().toString();

        showProgressDialog();
        new ContactMangController(this).sendMail(Utility.SEND_MAIL_TO, strmsgBody, etSubject.getText().toString(), "", this);

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        dismissDialog();
        clear();
        Snackbar.make(etSubject, response.getMessage(), Snackbar.LENGTH_SHORT).show();


    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etSubject, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void clear() {
        etSubject.setText("");
        etBody.setText("");

    }
}
