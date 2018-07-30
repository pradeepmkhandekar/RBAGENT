package com.rupeeboss.rba.businessloan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;

public class BusinessLoanMenuActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout llTribebl, llAdityabl, llEdelweissbl, llotherBusinessLoan, lllendingkartbl, lltatabl, llhdfcBusinessLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_loan_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
        addListener();
    }

    private void addListener() {
        llTribebl.setOnClickListener(this);
        llAdityabl.setOnClickListener(this);
        llEdelweissbl.setOnClickListener(this);
        llotherBusinessLoan.setOnClickListener(this);
        lllendingkartbl.setOnClickListener(this);
        lltatabl.setOnClickListener(this);
        llhdfcBusinessLoan.setOnClickListener(this);
    }

    private void init_widgets() {
        llTribebl = (LinearLayout) findViewById(R.id.llTribebl);
        llAdityabl = (LinearLayout) findViewById(R.id.llAdityabl);
        llEdelweissbl = (LinearLayout) findViewById(R.id.llEdelweissbl);
        llotherBusinessLoan = (LinearLayout) findViewById(R.id.llotherBusinessLoan);
        lllendingkartbl = (LinearLayout) findViewById(R.id.lllendingkartbl);
        lltatabl = (LinearLayout) findViewById(R.id.lltatabl);
        llhdfcBusinessLoan = (LinearLayout) findViewById(R.id.llhdfcBusinessLoan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llTribebl:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/tribe")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "TRIBE Business Loan"));
                break;
            case R.id.llAdityabl:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "https://www.abfldirect.com/?utm_source=RUPEEBOSS&utm_medium=DSA&utm_campaign=DirectSME#/smeLanding")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "Aditya Birla Loan"));
                break;
            case R.id.llEdelweissbl:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/edelweiss")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "Edelweiss Business Loan"));
                break;
            case R.id.lllendingkartbl:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/lendingkart")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "Lendingkart Business Loan"));
                break;
            case R.id.lltatabl:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/tata-capital-business-loan")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "TATA Business Loan"));
                break;
            case R.id.llotherBusinessLoan:
                /*startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/business-loan")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "Business Loan"));*/
                startActivity(new Intent(this, BLActivity.class));
                break;
            case R.id.llhdfcBusinessLoan:
                startActivity(new Intent(this, BusinessLoanWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/hdfc-business-loan")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "HDFC Business Loan"));
                break;

        }
    }
}
