package com.rupeeboss.rba.sharemessage;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.smslead.SmsLead;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.SmsLeadEntity;
import com.rupeeboss.rba.core.request.requestentity.LeadMobEntity;
import com.rupeeboss.rba.core.request.requestentity.SendSmsRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SmsLeadRequestEntity;
import com.rupeeboss.rba.core.response.SendSmsMobileResponse;
import com.rupeeboss.rba.core.response.SmsLeadResponse;

import java.util.ArrayList;
import java.util.List;

public class LeadSmsActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {

    Button btnSubmit;
    RecyclerView leadSmsRecycler;
    LeadSmsAdapter mAdapter;
    List<SmsLeadEntity> smsLeadEntities;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 1, totalItems;
    String loanType, message;
    List<LeadMobEntity> checkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_sms);

        loanType = getIntent().getStringExtra("LOAN_TYPE");
        message = getIntent().getStringExtra("MESSAGE");

        initialize_widgets();

        addListener();


        SmsLeadRequestEntity smsLeadRequestEntity = new SmsLeadRequestEntity();
        smsLeadRequestEntity.setCode(new LoginFacade(this).getUser().getEmpCode());
        smsLeadRequestEntity.setPgNo(page);
        smsLeadRequestEntity.setType(loanType);
        smsLeadRequestEntity.setBrokerId("" + new LoginFacade(this).getUser().getBrokerId());
        showProgressDialog();
        new SmsLead().getLeadSms(smsLeadRequestEntity, LeadSmsActivity.this);


    }

    private void addListener() {
        btnSubmit.setOnClickListener(this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        leadSmsRecycler.setLayoutManager(mLayoutManager);

        leadSmsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findLastVisibleItemPosition();
                    Log.v("onScrolled", "visibleItemCount = " + visibleItemCount + " pastVisiblesItems = " + pastVisiblesItems + " totalItemCount =" + totalItemCount);
                    if (loading) {
                        if (totalItemCount <= (pastVisiblesItems + visibleItemCount)) {
                            loading = false;
                            Log.v("onScrolled", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            SmsLeadRequestEntity smsLeadRequestEntity = new SmsLeadRequestEntity();
                            smsLeadRequestEntity.setCode(new LoginFacade(LeadSmsActivity.this).getUser().getEmpCode());
                            smsLeadRequestEntity.setPgNo(++page);
                            smsLeadRequestEntity.setType(loanType);
                            smsLeadRequestEntity.setBrokerId("" + new LoginFacade(LeadSmsActivity.this).getUser().getBrokerId());
                            Toast.makeText(LeadSmsActivity.this, pastVisiblesItems + "/" + totalItems, Toast.LENGTH_SHORT).show();
                            new SmsLead().getLeadSms(smsLeadRequestEntity, LeadSmsActivity.this);
                        }
                    }
                }
            }
        });
    }

    private void initialize_widgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        smsLeadEntities = new ArrayList<>();
        leadSmsRecycler = (RecyclerView) findViewById(R.id.leadSmsRecycler);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        checkedList = new ArrayList<LeadMobEntity>();

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof SmsLeadResponse) {
            if (response.getStatusId() == 0) {
                if (((SmsLeadResponse) response).getResult().getLstLeads() != null) {
                    if (mAdapter == null) {
                        smsLeadEntities = ((SmsLeadResponse) response).getResult().getLstLeads();
                        totalItems = ((SmsLeadResponse) response).getResult().getTotalCount();
                        mAdapter = new LeadSmsAdapter(LeadSmsActivity.this, smsLeadEntities);
                        leadSmsRecycler.setAdapter(mAdapter);
                    } else {
                        smsLeadEntities.addAll(((SmsLeadResponse) response).getResult().getLstLeads());
                        mAdapter.notifyDataSetChanged();
                        loading = true;
                    }
                }
            } else {
                leadSmsRecycler.setAdapter(null);
                loading = false;
                //Snackbar.make(et_date, "No data available", Snackbar.LENGTH_SHORT).show();
            }
        } else if (response instanceof SendSmsMobileResponse) {
            if (response.getStatusId() == 0) {
                finish();
                Snackbar.make(btnSubmit, response.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(btnSubmit, response.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            if (checkedList != null && checkedList.size() > 0) {
                SendSmsRequestEntity sendSmsRequestEntity = new SendSmsRequestEntity();
                sendSmsRequestEntity.setLstleadMob(checkedList);
                sendSmsRequestEntity.setMsg(message);
                sendSmsRequestEntity.setType(loanType);
                showProgressDialog();
                new SmsLead().sendSmsMobileNoList(sendSmsRequestEntity, LeadSmsActivity.this);
            } else {
                Snackbar.make(btnSubmit, "Select alteast one", Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    public void checkedContact(LeadMobEntity leadMobEntity, boolean isChecked) {

        if (isChecked) {
            checkedList.add(leadMobEntity);
        } else {
            int indexOfSelected = 0;
            for (int i = 0; i < checkedList.size(); i++) {
                if (checkedList.get(i).getLeadId().equals(leadMobEntity.getLeadId())) {
                    indexOfSelected = i;
                    break;
                }
            }
            //int getIndex = checkedList.indexOf(leadMobEntity.getLeadId());
            checkedList.remove(indexOfSelected);
        }

    }
}
