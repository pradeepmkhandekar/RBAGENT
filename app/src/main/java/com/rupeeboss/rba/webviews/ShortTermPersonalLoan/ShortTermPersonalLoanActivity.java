package com.rupeeboss.rba.webviews.ShortTermPersonalLoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.personalloan.IIFLWebviewActivity;
import com.rupeeboss.rba.personalloan.LoanPersonalActivity;

public class ShortTermPersonalLoanActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout llearly, llsenseLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_term_personal_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();



    }
    private void init_widgets() {
        llearly = (LinearLayout) findViewById(R.id.llearly);
        llsenseLoan = (LinearLayout) findViewById(R.id.llsenseLoan);

        llearly.setOnClickListener(this);
        llsenseLoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llearly:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "early").putExtra("TITLE", "Early Salary"));
                break;
            case R.id.llsenseLoan:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "PaySense").putExtra("TITLE", "Pay Sense"));
                break;

        }
    }
}
