package com.rupeeboss.rba.EmiCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.EmiCalculator.businessloan.BusinessLoanCalActivity;
import com.rupeeboss.rba.EmiCalculator.workcapital.WorkingCapitalCalculatorActivity;
import com.rupeeboss.rba.R;

public class EmiMenuActivity extends BaseActivity implements View.OnClickListener  {

    LinearLayout Homeloancal, Emical, personalLoancal, businessLoancal,workingCapitalcal,Incomecal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initialize();
    }
    private void initialize() {
        Homeloancal = (LinearLayout) findViewById(R.id.Homeloancal);
        Emical = (LinearLayout) findViewById(R.id.Emical);

        personalLoancal = (LinearLayout) findViewById(R.id.personalLoancal);
        businessLoancal = (LinearLayout) findViewById(R.id.businessLoancal);
        workingCapitalcal = (LinearLayout) findViewById(R.id.workingCapitalcal);
        Incomecal = (LinearLayout) findViewById(R.id.Incomecal);

        Homeloancal.setOnClickListener(this);
        Emical.setOnClickListener(this);
        personalLoancal.setOnClickListener(this);
        businessLoancal.setOnClickListener(this);

        workingCapitalcal.setOnClickListener(this);
        Incomecal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.Emical:
                startActivity(new Intent(EmiMenuActivity.this, EmiCalcActivity.class));
                break;
            case  R.id.Homeloancal:
                startActivity(new Intent(EmiMenuActivity.this, HomeEMICalcActivity.class));
                break;
            case  R.id.personalLoancal:
                startActivity(new Intent(EmiMenuActivity.this, PersonalEMICalcActivity.class));
                break;
            case  R.id.businessLoancal:
                startActivity(new Intent(EmiMenuActivity.this, BusinessLoanCalActivity.class));
                break;
            case  R.id.workingCapitalcal:
                startActivity(new Intent(EmiMenuActivity.this, WorkingCapitalCalculatorActivity.class));
                break;
            case  R.id.Incomecal:
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
