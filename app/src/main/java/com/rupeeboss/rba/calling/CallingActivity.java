package com.rupeeboss.rba.calling;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.calling.followup.FollowUpActivity;
import com.rupeeboss.rba.calling.history.HistoryActivity;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.BreakDetails.BreakDetails;
import com.rupeeboss.rba.core.controller.dialer.Dialer;
import com.rupeeboss.rba.core.database.AudioRecorderFacade;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.DialerEntity;
import com.rupeeboss.rba.core.response.BreakDetailsResponse;
import com.rupeeboss.rba.core.response.DialerResponse;
import com.rupeeboss.rba.rbdialerpad.AudioRecorder;
import com.rupeeboss.rba.rbfeedback.FeedBackActivity;
import com.rupeeboss.rba.utility.Utility;

import java.math.BigDecimal;

public class CallingActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    TelephonyManager telephonyManager;
    PhoneStateListener callStateListener;


    AudioRecorder audioRecorder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AudioEntity pkAudioEntity;


    TextView txtTime;
    private CountDownTimer countDownTimer;
    public static int breaktype;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;
    static int pauseTime;


    Button btnHistory, btnPause, btnFollowup, btnPunchOut;
    Button btnTeaBreak, btnPersonalBreak, btnWashRoomBreak, btnLunchBreak;


    DialerEntity dialerEntity;

    TextView txtName, txtProduct, txtLoanFrom;
    TextView txtNoLeadData;
    LinearLayout ll_LeadData, ll_timer;
    //File audiofile = null;
    static final String TAG = "MediaRecording";
    LoginFacade loginFacade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initialise_widget();
        setListner();
        loginFacade = new LoginFacade(CallingActivity.this);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Utility.CALL_STATUS, "NO");
        editor.putString(Utility.IS_FEEDBACK, "NO");
        editor.commit();

    }


    @Override
    protected void onPause() {

        if (breaktype == 0) {
            Log.d("DialerActivity", "Timer Stopped");
            pauseTimer();
            pauseTextChange("pause");
        }

        super.onPause();
    }


    @Override
    protected void onResume() {

        if (sharedPreferences.getString(Utility.IS_FEEDBACK, "").matches("NO") && sharedPreferences.getString(Utility.CALL_STATUS, "").matches("NO")) {
            Log.d("DialerActivity", "Fetching Data for calling");
            if (breaktype == 0) {
                showProgressDialog();
                new Dialer().getObject().getLeadData("" + new LoginFacade(this).getUser().getEmpCode(), this, this);
            }
        }
        super.onResume();
    }

    private void setListner() {

        btnHistory.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnTeaBreak.setOnClickListener(this);
        btnPersonalBreak.setOnClickListener(this);
        btnWashRoomBreak.setOnClickListener(this);
        btnLunchBreak.setOnClickListener(this);
        btnFollowup.setOnClickListener(this);
        btnPunchOut.setOnClickListener(this);
    }

    void initialise_widget() {
        btnPunchOut = (Button) findViewById(R.id.btnPunchOut);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtName = (TextView) findViewById(R.id.txtName);
        txtProduct = (TextView) findViewById(R.id.txtProduct);
        txtLoanFrom = (TextView) findViewById(R.id.txtLoanFrom);
        btnFollowup = (Button) findViewById(R.id.btnFollowup);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnTeaBreak = (Button) findViewById(R.id.btnTeaBreak);
        btnPersonalBreak = (Button) findViewById(R.id.btnPersonalBreak);
        btnWashRoomBreak = (Button) findViewById(R.id.btnWashRoomBreak);
        btnLunchBreak = (Button) findViewById(R.id.btnLunchBreak);

        txtNoLeadData = (TextView) findViewById(R.id.tv_noLeadData);
        ll_LeadData = (LinearLayout) findViewById(R.id.ll_leadDetails);
        ll_timer = (LinearLayout) findViewById(R.id.ll_timer);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnFollowup:
                setAllButtonColor();
                btnFollowup.setBackground(getResources().getDrawable(R.drawable.bg3));
                if (breaktype == 0)
                    pauseTimer();
                startActivity(new Intent(this, FollowUpActivity.class));
                break;
            case R.id.btnHistory:
                setAllButtonColor();
                btnHistory.setBackground(getResources().getDrawable(R.drawable.bg3));
                if (breaktype == 0)
                    pauseTimer();
                if (dialerEntity != null)
                    startActivity(new Intent(this, HistoryActivity.class).putExtra("PHONE_NUMBER", dialerEntity.getMobNo()));
                break;
            case R.id.btnPause:


                if (btnPause.getText().toString().equals("Resume")) {
                    if (breaktype == 0) {
                        pauseTimer();
                        startTimer(pauseTime, 1 * 1000);
                        pauseTextChange("Pause");
                    } else {
                        pauseTimer();
                        breaktype = 0;
                        startTimer(10 * 1000, 1 * 1000);
                        pauseTextChange("Pause");
                    }
                    enableDisableButton(true);
                } else {
                    pauseTimer();
                    pauseTextChange("Resume");
                }
                break;
            case R.id.btnTeaBreak:
                setAllButtonColor();
                btnTeaBreak.setBackground(getResources().getDrawable(R.drawable.bg3));
                breaktype = 1;
                showProgressDialog();
                new BreakDetails().sendBreakDetails("" + loginFacade.getUser().getEmpCode(), "2", "0", this);
                pauseTimer();

                break;
            case R.id.btnPersonalBreak:
                setAllButtonColor();
                btnPersonalBreak.setBackground(getResources().getDrawable(R.drawable.bg3));
                breaktype = 2;
                showProgressDialog();
                new BreakDetails().sendBreakDetails("" + loginFacade.getUser().getEmpCode(), "4", "0", this);
                pauseTimer();

                break;
            case R.id.btnWashRoomBreak:
                breaktype = 3;
                showProgressDialog();
                pauseTimer();
                break;
            case R.id.btnLunchBreak:
                setAllButtonColor();
                btnLunchBreak.setBackground(getResources().getDrawable(R.drawable.bg3));
                breaktype = 4;
                showProgressDialog();
                new BreakDetails().sendBreakDetails("" + loginFacade.getUser().getEmpCode(), "3", "0", this);
                pauseTimer();
                break;
            case R.id.btnPunchOut:
                breaktype = 5;
                editor.remove(Utility.IS_FEEDBACK);
                editor.remove(Utility.CALL_STATUS);
                editor.remove(Utility.CALL_STATUS_FOLLOWUP);
                editor.apply();
                //new BreakDetails().sendBreakDetails(Utility.EmpCode, "5", "0", this);
        }
    }

    private void setAllButtonColor() {
        btnHistory.setBackgroundColor(getResources().getColor(R.color.white_transparent));
        btnFollowup.setBackgroundColor(getResources().getColor(R.color.white_transparent));
        btnTeaBreak.setBackgroundColor(getResources().getColor(R.color.white_transparent));
        btnPersonalBreak.setBackgroundColor(getResources().getColor(R.color.white_transparent));
        btnLunchBreak.setBackgroundColor(getResources().getColor(R.color.white_transparent));
    }

    private void BindData() {
        if (dialerEntity.getMobNo() != null) {
            ll_LeadData.setVisibility(View.VISIBLE);
            ll_timer.setVisibility(View.VISIBLE);
            txtNoLeadData.setVisibility(View.GONE);
            enableDisableButton(true);
            txtName.setText("" + dialerEntity.getCustName());
            txtProduct.setText("" + dialerEntity.getProdName());
            txtLoanFrom.setText("" + BigDecimal.valueOf(dialerEntity.getLoanAmnt()).toPlainString());
        } else {
            enableDisableButton(false);
            ll_LeadData.setVisibility(View.GONE);
            ll_timer.setVisibility(View.GONE);
            txtNoLeadData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        dismissDialog();
        if (response instanceof DialerResponse) {
            if (((DialerResponse) response).getResult() != null) {
                dialerEntity = ((DialerResponse) response).getResult();
                BindData();
                pauseTimer();
                breaktype = 0;
                if (dialerEntity.getMobNo() != null)
                    startTimer(10 * 1000, 1 * 1000);
            }
        }
        if (response instanceof BreakDetailsResponse) {
            if (response.getStatusId() == 0) {
                if (breaktype == 1) {
                    startTimer(loginFacade.getBreakDetailsList().get(2).getTime() * 1000, 1 * 1000);
                    enableDisableButton(false);
                    pauseTextChange("Resume");
                } else if (breaktype == 2) {
                    startTimer(loginFacade.getBreakDetailsList().get(4).getTime() * 1000, 1 * 1000);
                    enableDisableButton(false);
                    pauseTextChange("Resume");
                } /*else if (breaktype == 3) {
                    startTimer(Utility.WASH * 1000, 1 * 1000);
                    enableDisableButton(false);
                    pauseTextChange("Resume");

                }*/ else if (breaktype == 4) {
                    startTimer(loginFacade.getBreakDetailsList().get(3).getTime() * 1000, 1 * 1000);
                    enableDisableButton(false);
                    pauseTextChange("Resume");
                } else if (breaktype == 5) {
                    clearDialerCache();
                    System.exit(0);
                }
            } else {
                Snackbar.make(txtName, response.getStatus(), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        editor.putString(Utility.CALL_STATUS, "NO");
        editor.putString(Utility.IS_FEEDBACK, "NO");
        editor.commit();
        Snackbar.make(txtName, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void pauseTextChange(String btnText) {
        btnPause.setText(btnText);
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            switch (breaktype) {
                case 0:
                    //dialCall("198");
                    if (dialerEntity != null) {
                        if (dialerEntity.getMobNo() != "" && dialerEntity.getMobNo() != null) {
                            dialCall(dialerEntity.getMobNo());
                        }
                    }
                    break;
                case 1:
                    showDialogWithMessage("Tea Break Finished");
                    break;
                case 2:
                    showDialogWithMessage("Personal work time finished");
                    break;
                case 3:
                    showDialogWithMessage("Washroom break time over");
                    break;
                case 4:
                    showDialogWithMessage("Lunch Break finished");
                    break;
            }

        }

        @Override
        public void onTick(long millisUntilFinished) {
            txtTime.setText("" + millisUntilFinished / 1000);
            pauseTime = (int) (millisUntilFinished);

        }
    }

    private void enableDisableButton(boolean b) {
        btnTeaBreak.setEnabled(b);
        btnLunchBreak.setEnabled(b);
        btnPersonalBreak.setEnabled(b);
        btnWashRoomBreak.setEnabled(b);
        /*if (!b) {
            btnTeaBreak.setAlpha(0.6f);
            btnLunchBreak.setAlpha(0.6f);
            btnWashRoomBreak.setAlpha(0.6f);
            btnPersonalBreak.setAlpha(0.6f);
        } else {
            btnTeaBreak.setAlpha(1.0f);
            btnLunchBreak.setAlpha(1.0f);
            btnWashRoomBreak.setAlpha(1.0f);
            btnPersonalBreak.setAlpha(1.0f);
        }
*/
    }

    private void showDialogWithMessage(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CallingActivity.this);
        alertDialog.setTitle(msg);
        alertDialog.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                enableDisableButton(true);
                pauseTextChange("Pause");
                breaktype = 0;
                startTimer(10 * 1000, 1 * 1000);
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void startTimer(int startTime, int interval) {

        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();
        txtTime.setText(txtTime.getText() + String.valueOf(startTime / 1000));

    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void dialCall(final String mbNumber) {
        pauseTimer();


        callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
/*
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy_HH:mm:ss");
                Calendar calobj = Calendar.getInstance();*/
                //System.out.println(df.format(calobj.getTime()));
                if (state == TelephonyManager.CALL_STATE_RINGING) {

                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

                    try {
                        if (audioRecorder == null) {
                            audioRecorder = new AudioRecorder();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                audioRecorder.startRecording(dialerEntity.getMobNo(), MediaRecorder.AudioSource.MIC, CallingActivity.this);
                            else
                                audioRecorder.startRecording(dialerEntity.getMobNo(), MediaRecorder.AudioSource.VOICE_CALL, CallingActivity.this);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                if (state == TelephonyManager.CALL_STATE_IDLE) {

                    if (sharedPreferences.getString(Utility.CALL_STATUS, "").matches("YES")) {

                        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_NONE);

                        editor.putString(Utility.CALL_STATUS, "NO");

                        try {
                            if (audioRecorder != null) {
                                audioRecorder.stopRecording();
                                pkAudioEntity = saveRecordingToDb(audioRecorder.getFilePath(), "" + loginFacade.getUser().getEmpCode(), mbNumber);
                                audioRecorder = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        if (dialerEntity != null) {

                            if (dialerEntity.getMobNo() != null || !dialerEntity.getMobNo().matches("")) {
                                pauseTimer();
                                editor.putString(Utility.IS_FEEDBACK, "YES");
                                startActivity(new Intent(CallingActivity.this, FeedBackActivity.class)
                                        .putExtra("PHONE_DIAL_NUMBER", dialerEntity.getMobNo())
                                        .putExtra("LEAD_ID", dialerEntity.getLeadId())
                                        .putExtra("NAME", dialerEntity.getCustName()));
                            }
                        } else {
                            Toast.makeText(CallingActivity.this, "No Number Found ", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        editor.putString(Utility.CALL_STATUS, "YES");
                    }
                    editor.commit();
                }
            }
        };
        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mbNumber));
        startActivity(intent);
    }

    private AudioEntity saveRecordingToDb(String filePath, String empCode, String mobile) {
        AudioEntity audioEntity = new AudioEntity();
        try {

            audioEntity.setFile_name(filePath);
            audioEntity.setUser_id(empCode);
            audioEntity.setUploaded(false);
            audioEntity.setCreatedDate();
            audioEntity.setMobile(mobile);
            audioEntity.setLead_id(dialerEntity.getLeadId());
            new AudioRecorderFacade(this).insertAudioRecord(audioEntity);
            return audioEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clearDialerCache();

    }

    private void clearDialerCache() {
        breaktype = 0;
        editor.remove(Utility.CALL_STATUS);
        editor.remove(Utility.IS_FEEDBACK);
        editor.remove(Utility.CALL_STATUS_FOLLOWUP);
        editor.commit();
    }


}
