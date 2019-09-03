package com.rupeeboss.rba.contactToManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.contactmanager.ContactMangController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.ContactMangEntity;
import com.rupeeboss.rba.core.response.ContactToMangerResponse;
import com.rupeeboss.rba.fragment.SendMailDialogFragment;
import com.rupeeboss.rba.utility.Utility;

public class ContactToManager extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    TextView txtRepEmp, txtDesig, txtEmail, txtMobile;
    ImageView ivCalling, ivEmail;
    LoginFacade loginFacade;
    ContactMangEntity contactMangEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_to_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loginFacade = new LoginFacade(this);
        initialize();
        showProgressDialog();

        new ContactMangController(this).getEmpSupervisorDtls(loginFacade.getPanNumber(), ContactToManager.this);
    }

    private void initialize() {

        txtRepEmp = (TextView) findViewById(R.id.txtRepEmp);
        txtDesig = (TextView) findViewById(R.id.txtDesig);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtMobile = (TextView) findViewById(R.id.txtMobile);

         ivCalling = (ImageView) findViewById(R.id.ivCalling);
         ivEmail = (ImageView) findViewById(R.id.ivEmail);

        ivCalling.setOnClickListener(this);
        ivEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Utility.hideKeyBoard(view, ContactToManager.this);
        if (view.getId() == R.id.ivCalling) {
            getCalled();
        } else if (view.getId() == R.id.ivEmail) {
            showDialog();
        }
    }

    private void getCalled() {

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        if( txtMobile.getText()!="") {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + txtMobile.getText()));
            startActivity(intent);
        }else {
            Snackbar.make(txtRepEmp,"Mobile no. not available", Snackbar.LENGTH_SHORT).show();
        }
    }
    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof ContactToMangerResponse) {
            if (response.getStatus_Id() == 0) {
                 contactMangEntity = ((ContactToMangerResponse) response).getResult();

                txtRepEmp.setText(""+ contactMangEntity.getReportingEmpName());
                txtDesig.setText(""+ contactMangEntity.getReportingEmpDesignation());
                txtEmail.setText(""+ contactMangEntity.getReportingEmpEmail());
                txtMobile.setText(""+ contactMangEntity.getReportingEmpMobile());
            }
            else {
                clear();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        clear();
        Snackbar.make(txtRepEmp, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    public void showDialog() {

        if(contactMangEntity != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Utility.CONTACT_MANAGER, contactMangEntity);
            FragmentManager fm = getSupportFragmentManager();
            SendMailDialogFragment sendMailDialogFragment = new SendMailDialogFragment();
            sendMailDialogFragment.setArguments(bundle);
            sendMailDialogFragment.show(fm, "MY QUEERY");
        }else{
            Snackbar.make(txtRepEmp, "Insufficient Data", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void clear()
    {
        txtRepEmp.setText("");
        txtDesig.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
    }
}
