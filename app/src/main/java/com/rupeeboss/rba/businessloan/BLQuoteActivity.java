package com.rupeeboss.rba.businessloan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.request.requestentity.BuisnessLoanCalRequest;
import com.rupeeboss.rba.core.response.BLQuoteResponse;

public class BLQuoteActivity extends AppCompatActivity {
    BLQuoteResponse blQuoteResponse;
    BuisnessLoanCalRequest buisnessLoanCalRequest;
    RecyclerView rvBl;
    BlQuoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blquote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvBl = (RecyclerView) findViewById(R.id.rvBl);
        rvBl.setLayoutManager(new LinearLayoutManager(BLQuoteActivity.this));
        blQuoteResponse = getIntent().getParcelableExtra("BL_RESPONSE");
        buisnessLoanCalRequest = getIntent().getParcelableExtra("BL_REQUEST");
        mAdapter = new BlQuoteAdapter(BLQuoteActivity.this, blQuoteResponse);
        rvBl.setAdapter(mAdapter);
    }
}
