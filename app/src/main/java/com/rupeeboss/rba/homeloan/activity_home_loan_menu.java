package com.rupeeboss.rba.homeloan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.loan.LoanActivity;
import com.rupeeboss.rba.personalloan.IIFLWebviewActivity;
import com.rupeeboss.rba.personalloan.LoanPersonalActivity;

public class activity_home_loan_menu extends BaseActivity implements View.OnClickListener {

    LinearLayout llkotakhl, llotherLoan,llyesLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_loan_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
    }

    private void init_widgets() {
        llkotakhl = (LinearLayout) findViewById(R.id.llkotakhl);
        llotherLoan = (LinearLayout) findViewById(R.id.llotherLoan);
        llyesLoan = (LinearLayout) findViewById(R.id.llyesLoan);

        llkotakhl.setOnClickListener(this);
        llotherLoan.setOnClickListener(this);
        llyesLoan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llkotakhl:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values","kotakhl").putExtra("TITLE", "KOTAK Home Loan"));

                break;
            case R.id.llyesLoan:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values","yeshlLoan").putExtra("TITLE", "YES BANK Home Loan"));
                break;

            case R.id.llotherLoan:
                startActivity(new Intent(this, LoanActivity.class));
                break;

        }
    }
}
