package com.rupeeboss.rba.mylead;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.smslead.SmsLead;
import com.rupeeboss.rba.core.database.AudioRecorderFacade;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.LeadDetailsEntity;
import com.rupeeboss.rba.core.model.MyLeadResult;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.response.MyLeadResponse;
import com.rupeeboss.rba.rbdialerpad.AudioRecorder;
import com.rupeeboss.rba.rbfeedback.FeedBackActivity;
import com.rupeeboss.rba.utility.Utility;

import java.util.List;

public class MyLeadActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {
    RecyclerView myLeadRecycler;
    MyLeadAdapter mAdapter;
    List<LeadDetailsEntity> leadDetailsEntityList;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 1, totalItems;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //AudioRecorder audioRecorder;

    TelephonyManager telephonyManager;
    PhoneStateListener callStateListener;
    AudioEntity pkAudioEntity;

    AudioRecorder audioRecorder;
    String mbNumber;
    String custName;
    TelephonyManager manager_follow;
    LoginFacade loginFacade;
    String brokerId, empCode;
    MyLeadResult myLeadResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lead);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginFacade = new LoginFacade(this);
        empCode = loginFacade.getUser().getEmpCode();
        brokerId = "" + loginFacade.getUser().getBrokerId();
        if (getIntent().hasExtra("BROKER_ID")) {
            myLeadResult = getIntent().getParcelableExtra("MY_LEAD_RESPONSE");
            brokerId = "" + getIntent().getIntExtra("BROKER_ID", 0);
            empCode = "";
        }
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Utility.CALL_STATUS_FOLLOWUP, "NO");
        editor.commit();
        initilize();
        addListener();


    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLeadRequestEntity myLeadRequestEntity = new MyLeadRequestEntity();

        if (getIntent().hasExtra("BROKER_ID")) {
            /*int brokerId = getIntent().getIntExtra("BROKER_ID", 0);
            if (brokerId != 0) {
                myLeadRequestEntity.setBrokerId("" + brokerId);
                myLeadRequestEntity.setCode("");
            }*/
            myLeadResult = getIntent().getParcelableExtra("MY_LEAD_RESPONSE");
            leadDetailsEntityList = myLeadResult.getLstLeads();
            totalItems = myLeadResult.getTotalCount();
            mAdapter = new MyLeadAdapter(this, leadDetailsEntityList);
            myLeadRecycler.setAdapter(mAdapter);
        } else {

            myLeadRequestEntity.setBrokerId(brokerId);
            myLeadRequestEntity.setCode(empCode);
            myLeadRequestEntity.setPgNo(page);
            showProgressDialog();
            new SmsLead().getMyLead(myLeadRequestEntity, MyLeadActivity.this);
        }


    }

    private void initilize() {
        myLeadRecycler = (RecyclerView) findViewById(R.id.myLeadRecycler);
    }

    private void addListener() {
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        myLeadRecycler.setLayoutManager(mLayoutManager);

        myLeadRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            MyLeadRequestEntity myLeadRequestEntity = new MyLeadRequestEntity();
                            myLeadRequestEntity.setBrokerId(brokerId);
                            myLeadRequestEntity.setCode(empCode);
                            myLeadRequestEntity.setPgNo(++page);
                            Toast.makeText(MyLeadActivity.this, totalItemCount + "/" + totalItems, Toast.LENGTH_SHORT).show();

                            if (totalItemCount <= totalItems) {

                                new SmsLead().getMyLead(myLeadRequestEntity, MyLeadActivity.this);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            if (leadDetailsEntityList != null)
                leadDetailsEntityList.clear();
            page = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof MyLeadResponse) {
            if (response.getStatusId() == 0) {
                if (((MyLeadResponse) response).getResult().getLstLeads() != null) {
                    if (mAdapter == null) {
                        leadDetailsEntityList = ((MyLeadResponse) response).getResult().getLstLeads();
                        totalItems = ((MyLeadResponse) response).getResult().getTotalCount();
                        mAdapter = new MyLeadAdapter(this, leadDetailsEntityList);
                        myLeadRecycler.setAdapter(mAdapter);
                    } else {
                        leadDetailsEntityList.addAll(((MyLeadResponse) response).getResult().getLstLeads());
                        mAdapter.notifyDataSetChanged();
                        loading = true;
                    }
                }
            } else {
                myLeadRecycler.setAdapter(null);
                loading = false;
                //Snackbar.make(et_date, "No data available", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

    }

    public void dialCall(final String mbNumber, String name, final int leadId) {

        this.mbNumber = mbNumber;
        this.custName = name;

        callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {

                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    Log.d("DialerActivity_New", "CALL_STATE_RINGING");
                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    Log.d("DialerActivity_New", "call running");
                    try {
                        if (audioRecorder == null)
                            audioRecorder = new AudioRecorder();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.MIC, MyLeadActivity.this);
                        else
                            audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.VOICE_CALL, MyLeadActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    if (sharedPreferences.getString(Utility.CALL_STATUS_FOLLOWUP, "NO").matches("YES")) {
                        Log.d("DialerActivity_New", "call ended");
                        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_NONE);
                        Log.d("DialerActivity_New", "unregistered broadcst receiver");
                        editor.putString(Utility.CALL_STATUS_FOLLOWUP, "NO");
                        try {
                            if (audioRecorder != null) {
                                audioRecorder.stopRecording();
                                pkAudioEntity = saveRecordingToDb(audioRecorder.getFilePath(), loginFacade.getUser().getEmpCode(), mbNumber, leadId);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(MyLeadActivity.this, FeedBackActivity.class)
                                .putExtra("PHONE_DIAL_NUMBER", mbNumber)
                                .putExtra("LEAD_ID", leadId)
                                .putExtra("NAME", custName));

                    }

                } else {
                    Log.d("DialerActivity_New", "phone is neither ringing nor in a call");
                    editor.putString(Utility.CALL_STATUS_FOLLOWUP, "YES");
                }
                editor.commit();

            }
        };
        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mbNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    private AudioEntity saveRecordingToDb(String filePath, String empCode, String mbNumber, int leadId) {
        AudioEntity audioEntity = new AudioEntity();
        try {

            audioEntity.setFile_name(filePath);
            audioEntity.setUser_id(empCode);
            audioEntity.setUploaded(false);
            audioEntity.setMobile(mbNumber);
            audioEntity.setCreatedDate();
            audioEntity.setLead_id(leadId);
            new AudioRecorderFacade(this).insertAudioRecord(audioEntity);
            return audioEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
