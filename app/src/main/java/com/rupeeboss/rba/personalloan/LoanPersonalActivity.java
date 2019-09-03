package com.rupeeboss.rba.personalloan;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;

/**
 * Created by IN-RB on 09-02-2017.
 */

public class LoanPersonalActivity extends BaseActivity implements   View.OnClickListener {

    ImageView ivloan_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_personal);
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

                startActivity(new Intent(LoanPersonalActivity.this, PersonalLoanActivity.class));
                //  startActivity(new Intent(LoanMenuActivity.this, LoanActivity.class));
                break;
        }
    }

}
