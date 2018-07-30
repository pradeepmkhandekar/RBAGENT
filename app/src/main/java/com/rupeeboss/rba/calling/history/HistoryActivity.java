package com.rupeeboss.rba.calling.history;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.history.History;
import com.rupeeboss.rba.core.model.LeadHstryDataEntity;
import com.rupeeboss.rba.core.response.HistoryResponse;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView recyclerHistory;
    HistoryAdapter mAdapter;

    String mobileNumber;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txtName = (TextView) findViewById(R.id.txtName);
        recyclerHistory = (RecyclerView) findViewById(R.id.recyclerHistory);
        recyclerHistory.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerHistory.setLayoutManager(mLayoutManager);

        mobileNumber = getIntent().getStringExtra("PHONE_NUMBER");

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog();
        new History().getHistory(mobileNumber, this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

       dismissDialog();
        if (response instanceof HistoryResponse) {
            if (((HistoryResponse) response).getResult() != null) {
                if (((HistoryResponse) response).getResult().getLeadHstryData() != null) {
                    List<LeadHstryDataEntity> list = new ArrayList<>();
                    list = ((HistoryResponse) response).getResult().getLeadHstryData();
                    if (list.size() != 0) {
                        mAdapter = new HistoryAdapter(this, list);
                        txtName.setText(list.get(0).getName());
                        recyclerHistory.setAdapter(mAdapter);
                    }
                }
            } else {
                Snackbar.make(recyclerHistory, "No Data ", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(recyclerHistory, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
