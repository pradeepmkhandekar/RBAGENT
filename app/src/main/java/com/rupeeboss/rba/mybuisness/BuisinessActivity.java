package com.rupeeboss.rba.mybuisness;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.mybuisness.MyBusinessController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.MyBusinessResponse;
import com.rupeeboss.rba.utility.Utility;

public class BuisinessActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    LinearLayout homeLoan, loanAgainstProperty, balanceTransfer, carLoan, personalLoan, businessLoan;
    LoginFacade loginFacade;
    MyBusinessResponse myBusinessResponse;
    String strTitle = "";
    String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisiness);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loginFacade = new LoginFacade(this);
        initalize();
    }

    private void initalize() {
        homeLoan = (LinearLayout) findViewById(R.id.homeLoan);
        loanAgainstProperty = (LinearLayout) findViewById(R.id.loanAgainstProperty);
        balanceTransfer = (LinearLayout) findViewById(R.id.balanceTransfer);

        carLoan = (LinearLayout) findViewById(R.id.carLoan);
        personalLoan = (LinearLayout) findViewById(R.id.personalLoan);
        businessLoan = (LinearLayout) findViewById(R.id.businessLoan);

        homeLoan.setOnClickListener(this);
        loanAgainstProperty.setOnClickListener(this);
        balanceTransfer.setOnClickListener(this);

        carLoan.setOnClickListener(this);
        personalLoan.setOnClickListener(this);
        businessLoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.homeLoan) {
            getData("1" ,getResources().getString(R.string.no_files_login));
        } else if (view.getId() == R.id.balanceTransfer) {
            getData("2",getResources().getString(R.string.no_files_sact));
        } else if (view.getId() == R.id.personalLoan) {
            getData("3",getResources().getString(R.string.no_files_dis));
        } else if (view.getId() == R.id.loanAgainstProperty) {
            getData("4",getResources().getString(R.string.unsec_dis_amnt));
        } else if (view.getId() == R.id.carLoan) {
            getData("5",getResources().getString(R.string.sec_dis_amnt));
        } else if (view.getId() == R.id.businessLoan) {
            getData("6",getResources().getString(R.string.tot_amnt_pay));
        }


    }

    private void getData(String type, String title) {
        showProgressDialog();
        strTitle = title;
        strType = type;
        new MyBusinessController(this).myBuisness(loginFacade.getUser().getEmpCode(), type, String.valueOf(loginFacade.getUser().getBrokerId()), BuisinessActivity.this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        dismissDialog();
        if (response instanceof MyBusinessResponse) {
            if (response.getStatus_Id() == 0) {
                myBusinessResponse = (MyBusinessResponse) response;
                Intent intent = new Intent(BuisinessActivity.this, BuisnessPopUpActivity.class);
                intent.putExtra(Utility.MY_BUSISNESS, myBusinessResponse.getResult());
                intent.putExtra(Utility.MY_BUSISNESS_HDR,strTitle);
                intent.putExtra(Utility.MY_BUSISNESS_type,strType);
                startActivity(intent);
            } else {
                Snackbar.make(homeLoan, response.getMsg(), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(homeLoan, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
