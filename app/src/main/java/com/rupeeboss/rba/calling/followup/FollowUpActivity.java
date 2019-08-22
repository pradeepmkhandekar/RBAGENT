package com.rupeeboss.rba.calling.followup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.followup.FollowUp;
import com.rupeeboss.rba.core.database.AudioRecorderFacade;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.FollowUpEntity;
import com.rupeeboss.rba.core.response.FollowUpResponse;
import com.rupeeboss.rba.rbdialerpad.AudioRecorder;
import com.rupeeboss.rba.rbfeedback.FeedBackActivity;
import com.rupeeboss.rba.utility.Utility;

import java.util.List;

public class FollowUpActivity extends BaseActivity implements IResponseSubcriber {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //AudioRecorder audioRecorder;

    TelephonyManager telephonyManager;
    PhoneStateListener callStateListener;
    AudioEntity pkAudioEntity;
    AudioRecorder audioRecorder;
    RecyclerView recyclerFollowup;
    FollowUpAdapter mAdapter;
    String mbNumber;
    String custName;
    LoginFacade loginFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FollowUp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loginFacade = new LoginFacade(this);
        recyclerFollowup = (RecyclerView) findViewById(R.id.recyclerFollowUp);
        recyclerFollowup.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerFollowup.setLayoutManager(mLayoutManager);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // setItemClickListener();
        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Utility.CALL_STATUS_FOLLOWUP, "NO");
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog();
        new FollowUp(FollowUpActivity.this).getFollowUp(loginFacade.getUser().getEmpCode(), this);
    }

    @Override
    protected void onPause() {
        //  manager_follow.listen(listener_follow, PhoneStateListener.LISTEN_NONE);
        //  listener_follow = null;
        super.onPause();

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
                            audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.MIC, FollowUpActivity.this);
                        else
                            audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.VOICE_CALL, FollowUpActivity.this);
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
                        startActivity(new Intent(FollowUpActivity.this, FeedBackActivity.class)
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
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mbNumber));
        startActivity(intent);
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {
        dismissDialog();
        if (response instanceof FollowUpResponse) {
            if (((FollowUpResponse) response).getResult() != null) {
                if (((FollowUpResponse) response).getResult().getLstFollowupLeads() != null) {
                    List<FollowUpEntity> followUpEntities = ((FollowUpResponse) response).getResult().getLstFollowupLeads();
                    // Log.d("folloUpEntities","Size = "+followUpEntities.size());
                    mAdapter = new FollowUpAdapter(this, followUpEntities);
                    recyclerFollowup.setAdapter(mAdapter);
                }
            }
        }
    }

    public void deleteNumberFromCallLog(Context context, String strNum) {
        getContentResolver().delete(CallLog.Calls.CONTENT_URI, CallLog.Calls.NUMBER + " = ?", new String[]{CallLog.Calls.getLastOutgoingCall(FollowUpActivity.this)});

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

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
