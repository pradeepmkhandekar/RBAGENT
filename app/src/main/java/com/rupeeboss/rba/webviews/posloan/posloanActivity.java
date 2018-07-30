package com.rupeeboss.rba.webviews.posloan;

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
import com.rupeeboss.rba.personalloan.IIFLWebviewActivity;

public class posloanActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout lliiflpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posloan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
    }
    private void init_widgets() {
        lliiflpos = (LinearLayout) findViewById(R.id.lliiflpos);


        lliiflpos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lliiflpos:
                startActivity(new Intent(this, IIFLWebviewActivity.class).putExtra("values", "iiflpos").putExtra("TITLE", "IIFL POS Loan"));
                break;

        }
    }
}
