package com.rupeeboss.rba.mybuisness;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.model.ResultDataMyBuisness;
import com.rupeeboss.rba.utility.Utility;

import java.math.BigDecimal;

public class BuisnessPopUpActivity extends BaseActivity implements View.OnClickListener {

    ResultDataMyBuisness myBuisness;
    RecyclerView buisnessRecycler;
    BuisnessPopUpAdapter mAdapter;
    Button btnClose;
    TextView txtTotCount, txtHdr,txtTotLoanAmnt;
    LinearLayout layout_tot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisness_pop_up);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        inialize();
        if (getIntent().hasExtra(Utility.MY_BUSISNESS)) {
            myBuisness = getIntent().getParcelableExtra(Utility.MY_BUSISNESS);
            String strHdr = getIntent().getStringExtra(Utility.MY_BUSISNESS_HDR);
            String strType = getIntent().getStringExtra(Utility.MY_BUSISNESS_type);
            mAdapter = new BuisnessPopUpAdapter(BuisnessPopUpActivity.this, myBuisness.getLstRpt());
            buisnessRecycler.setAdapter(mAdapter);

            txtTotCount.setText("Total Count : " + myBuisness.getTotalCount());
            txtTotLoanAmnt.setText("Total Loan Amount : " + BigDecimal.valueOf(myBuisness.getTotalLoanAmnt()).toPlainString());
            txtHdr.setText(strHdr);

            if(strType.equals("6"))
            {
                layout_tot.setVisibility(View.VISIBLE);
                txtTotLoanAmnt.setText("Total Payout Amount : " + BigDecimal.valueOf(myBuisness.getTotalLoanAmnt()).toPlainString());
            }
        }
       //BigDecimal.valueOf(quoteEntity.getLoan_eligible()).toPlainString());
    }

    private void inialize() {
        buisnessRecycler = (RecyclerView) findViewById(R.id.BuisnessRecycler);
        buisnessRecycler.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        buisnessRecycler.setLayoutManager(mLayoutManager);

        txtHdr = (TextView) findViewById(R.id.txtHdr);
        txtTotCount = (TextView) findViewById(R.id.txtTotCount);
        txtTotLoanAmnt = (TextView) findViewById(R.id.txtTotLoanAmnt);
        layout_tot = (LinearLayout)findViewById(R.id.layout_tot);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnClose) {
            this.finish();
        }

    }
}
