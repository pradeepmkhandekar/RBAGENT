package com.rupeeboss.rba.webviews.generalInsurance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferActivity;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferType;
import com.rupeeboss.rba.webviews.workingCapital.WorkingCapitalBalanceTrans;

public class GeneralInsuranceType extends BaseActivity implements View.OnClickListener {

    TextView tvHomeLoan, tvPersonalLoan, tvLAP;
    int product, brokerid;
    TextView txtWorkingCapital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_insurance_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize_widgets();
        brokerid = new LoginFacade(this).getUser().getBrokerId();


    }

    private void initialize_widgets() {
        tvHomeLoan = (TextView) findViewById(R.id.tvHomeLoan);
        txtWorkingCapital = (TextView) findViewById(R.id.txtWorkingCapital);
        tvPersonalLoan = (TextView) findViewById(R.id.tvPersonalLoan);
        tvLAP = (TextView) findViewById(R.id.tvLAP);
        tvHomeLoan.setOnClickListener(this);
        tvPersonalLoan.setOnClickListener(this);
        tvLAP.setOnClickListener(this);
        txtWorkingCapital.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHomeLoan:
                product = 1;
                startActivity(new Intent(GeneralInsuranceType.this, GeneralInsuranceActivity.class).putExtra("product", product).putExtra("brokerid", brokerid));
                break;
            case R.id.tvPersonalLoan:
                product = 2;
                startActivity(new Intent(GeneralInsuranceType.this, GeneralInsuranceActivity.class).putExtra("product", product).putExtra("brokerid", brokerid));
                break;
            case R.id.tvLAP:
                product = 3;
                startActivity(new Intent(GeneralInsuranceType.this, GeneralInsuranceActivity.class).putExtra("product", product).putExtra("brokerid", brokerid));
                break;
            case R.id.txtWorkingCapital:
                product = 4;
                startActivity(new Intent(GeneralInsuranceType.this, GeneralInsuranceActivity.class).putExtra("product", product).putExtra("brokerid", brokerid));
                break;

        }
    }
}
