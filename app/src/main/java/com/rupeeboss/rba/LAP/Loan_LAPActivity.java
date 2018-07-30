package com.rupeeboss.rba.LAP;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;

public class Loan_LAPActivity extends BaseActivity implements   View.OnClickListener {
    ImageView ivloan_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan__lap_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ivloan_add = (ImageView)findViewById(R.id.ivloan_add);
        ivloan_add.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivloan_add:
                startActivity(new Intent(Loan_LAPActivity.this, LAP_LoanActivity.class));
               // startActivity(new Intent(LoanActivity.this, HomeLoanActivity.class));
                //  startActivity(new Intent(LoanMenuActivity.this, LoanActivity.class));
                break;
        }
    }

}
