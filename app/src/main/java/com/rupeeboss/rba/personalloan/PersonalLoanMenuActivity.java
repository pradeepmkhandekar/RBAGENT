package com.rupeeboss.rba.personalloan;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;

public class PersonalLoanMenuActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout lliiflpl, llotherLoan, llrblLoan, llkotaklpl, llHdfcPl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();

    }

    private void init_widgets() {
        llHdfcPl = (LinearLayout) findViewById(R.id.llHdfcPl);
        lliiflpl = (LinearLayout) findViewById(R.id.lliiflpl);
        llotherLoan = (LinearLayout) findViewById(R.id.llotherLoan);
        llrblLoan = (LinearLayout) findViewById(R.id.llrblLoan);
        llkotaklpl = (LinearLayout) findViewById(R.id.llkotaklpl);
        lliiflpl.setOnClickListener(this);
        llotherLoan.setOnClickListener(this);
        llrblLoan.setOnClickListener(this);
        llkotaklpl.setOnClickListener(this);
        llHdfcPl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lliiflpl:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "iifl").putExtra("TITLE", "IIFL Personal Loan"));

                break;
            case R.id.llrblLoan:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "rbl").putExtra("TITLE", "RBL Personal Loan"));
                break;
            case R.id.llkotaklpl:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "kotak").putExtra("TITLE", "KOTAK Personal Loan"));
                break;
            case R.id.llotherLoan:
                startActivity(new Intent(this, LoanPersonalActivity.class));
                break;
            case R.id.llHdfcPl:
                startActivity(new Intent(this, CommonWebviewActivity.class)
                        .putExtra("URL", "http://www.rupeeboss.com/hdfc-personal-loan")
                        .putExtra("NAME", "HDFC PERSONAL LOAN")
                        .putExtra("TITLE", "HDFC PERSONAL LOAN"));
                break;

        }
    }
}
