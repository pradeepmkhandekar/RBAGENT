package com.rupeeboss.rba.webviews.ShortTermPersonalLoan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.personalloan.IIFLWebviewActivity;

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
