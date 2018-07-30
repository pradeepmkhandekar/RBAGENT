package com.rupeeboss.rba.calling.followup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.followuphistory.FollowUpHistory;
import com.rupeeboss.rba.core.model.FollowUpHistoryDetailsEntity;
import com.rupeeboss.rba.core.response.FollowUpHistoryDetailsResponse;
import com.rupeeboss.rba.rbfeedback.FeedBackActivity;

import java.util.List;


/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpHistoryActivity extends BaseActivity implements IResponseSubcriber {
    RecyclerView recyclerFollowUpHistory;
    FollowUpHistoryAdapter mAdapter;

    String mobileNumber, status;
    int leadId;
    String Empcode;
    TextView txtName;
    List<FollowUpHistoryDetailsEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followup_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FollowUp History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtName = (TextView) findViewById(R.id.txtName);
        recyclerFollowUpHistory = (RecyclerView) findViewById(R.id.recyclerFollowUpHistory);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerFollowUpHistory.setLayoutManager(mLayoutManager);
        recyclerFollowUpHistory.setHasFixedSize(true);

        mobileNumber = getIntent().getStringExtra("PHONE_NUMBER");
        leadId = getIntent().getIntExtra("LEAD_ID", 0);
        status = getIntent().getStringExtra("STATUS");

    }


    public void sendToFeedBack(String mName) {
        //FOLLOW_FEEDBACK
        startActivity(new Intent(this, FeedBackActivity.class)
                .putExtra("FOLLOW_PHONE_NUMBER", mobileNumber)
                .putExtra("NAME_FOLLOW_FEEDBACK", mName)
                .putExtra("LEAD_ID", leadId));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.followfeedback_menu, menu);
        if (status.contains("New")) {
            menu.getItem(0).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.nav_feedback) {
            String sName = "";
            if (list != null) {
                if (list.size() != 0) {
                    sName = list.get(0).getName();
                }
            }
            sendToFeedBack(sName);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog();
        new FollowUpHistory().getFollowUpHistoryDetails(leadId, this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        dismissDialog();
        if (response instanceof FollowUpHistoryDetailsResponse) {
            if (((FollowUpHistoryDetailsResponse) response).getResult() != null) {
                if (((FollowUpHistoryDetailsResponse) response).getResult().getFollowupHstryData() != null) {

                    list = ((FollowUpHistoryDetailsResponse) response).getResult().getFollowupHstryData();
                    mAdapter = new FollowUpHistoryAdapter(this, list);
                    recyclerFollowUpHistory.setAdapter(mAdapter);
                }
            } else {
                Snackbar.make(recyclerFollowUpHistory, "No Data ", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(recyclerFollowUpHistory, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
