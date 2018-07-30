package com.rupeeboss.rba.loan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.LAP.Loan_LAPActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.businessloan.BusinessLoanMenuActivity;
import com.rupeeboss.rba.homeloan.activity_home_loan_menu;
import com.rupeeboss.rba.personalloan.PersonalLoanMenuActivity;
import com.rupeeboss.rba.webviews.CarLoan.CarLoanActivity;
import com.rupeeboss.rba.webviews.ShortTermPersonalLoan.ShortTermPersonalLoanActivity;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferType;
import com.rupeeboss.rba.webviews.commPurchase.CommPurchaseActivity;
import com.rupeeboss.rba.webviews.posloan.posloanActivity;
import com.rupeeboss.rba.webviews.workingCapital.WorkingCapitalActivity;

public class LoanMenuActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout homeLoan, loanAgainstProperty, carLoan, personalLoan, businessLoan, balanceTransfer, workingCapital, commPurchase,posloan,ShortTermPersonalLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initialize();

    }

    private void initialize() {
        homeLoan = (LinearLayout) findViewById(R.id.homeLoan);
        loanAgainstProperty = (LinearLayout) findViewById(R.id.loanAgainstProperty);
        carLoan = (LinearLayout) findViewById(R.id.carLoan);
        personalLoan = (LinearLayout) findViewById(R.id.personalLoan);
        businessLoan = (LinearLayout) findViewById(R.id.businessLoan);
        balanceTransfer = (LinearLayout) findViewById(R.id.balanceTransfer);
        workingCapital = (LinearLayout) findViewById(R.id.workingCapital);
        commPurchase = (LinearLayout) findViewById(R.id.commPurchase);
        ShortTermPersonalLoan=(LinearLayout) findViewById(R.id.ShortTermPersonalLoan);
        posloan=(LinearLayout) findViewById(R.id.posloan);
        homeLoan.setOnClickListener(this);
        carLoan.setOnClickListener(this);
        personalLoan.setOnClickListener(this);
        businessLoan.setOnClickListener(this);
        loanAgainstProperty.setOnClickListener(this);
        balanceTransfer.setOnClickListener(this);

        workingCapital.setOnClickListener(this);
        commPurchase.setOnClickListener(this);
        ShortTermPersonalLoan.setOnClickListener(this);
        posloan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLoan:
                // startActivity(new Intent(LoanMenuActivity.this, HomeLoanActivity.class));
                startActivity(new Intent(LoanMenuActivity.this, activity_home_loan_menu.class));
                break;
            case R.id.loanAgainstProperty:
                startActivity(new Intent(LoanMenuActivity.this, Loan_LAPActivity.class));
                break;
            case R.id.carLoan:
                startActivity(new Intent(this, CarLoanActivity.class));
                break;
            case R.id.businessLoan:
                startActivity(new Intent(this, BusinessLoanMenuActivity.class));
                break;
            case R.id.personalLoan:
                startActivity(new Intent(LoanMenuActivity.this, PersonalLoanMenuActivity.class));
                break;
            case R.id.balanceTransfer:
                startActivity(new Intent(this, BalanceTransferType.class));
                break;

            case R.id.workingCapital:
                startActivity(new Intent(LoanMenuActivity.this, WorkingCapitalActivity.class));
                break;
            case R.id.commPurchase:
                startActivity(new Intent(LoanMenuActivity.this, CommPurchaseActivity.class));
                // Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ShortTermPersonalLoan:
                startActivity(new Intent(LoanMenuActivity.this, ShortTermPersonalLoanActivity.class));
                // Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.posloan:
                startActivity(new Intent(LoanMenuActivity.this, posloanActivity.class));
                // Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
