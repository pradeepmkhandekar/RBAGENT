package com.rupeeboss.rba.rbdialerpad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.dialer.Dialer;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.response.ValidateMobileResponse;
import com.rupeeboss.rba.rbaddlead.RbAddLeadActivity;
import com.rupeeboss.rba.rbfeedback.FeedBackActivity;
import com.rupeeboss.rba.utility.Utility;

public class RbDialerPadActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener, IResponseSubcriber {

    TelephonyManager telephonyManager;
    PhoneStateListener callStateListener;


    AudioRecorder audioRecorder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AudioEntity pkAudioEntity;

    EditText etPhoneNumber;
    Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4;
    Button btnNum5, btnNum6, btnNum7, btnNum8, btnNum9;

    ImageView btnCall, btnDelete;
    String mbNumber;

//    String[] perms = {"android.permission.WRITE_CALL_LOG",
//            "android.permission.CALL_PHONE",
//            "android.permission.READ_PHONE_STATE",
//            "android.permission.RECORD_AUDIO",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
//            "android.permission.ACCESS_FINE_LOCATION",
//            "android.permission.READ_CONTACTS",
//    }; //"android.permission.ACCESS_COARSE_LOCATION",

    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rb_dialer_pad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initialise_widget();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setListner();
    }

    private void initialise_widget() {
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        btnNum0 = (Button) findViewById(R.id.btnNum0);
        btnNum1 = (Button) findViewById(R.id.btnNum1);
        btnNum2 = (Button) findViewById(R.id.btnNum2);
        btnNum3 = (Button) findViewById(R.id.btnNum3);
        btnNum4 = (Button) findViewById(R.id.btnNum4);
        btnNum5 = (Button) findViewById(R.id.btnNum5);

        btnNum6 = (Button) findViewById(R.id.btnNum6);
        btnNum7 = (Button) findViewById(R.id.btnNum7);
        btnNum8 = (Button) findViewById(R.id.btnNum8);
        btnNum9 = (Button) findViewById(R.id.btnNum9);

        btnCall = (ImageView) findViewById(R.id.btnCall);
        btnDelete = (ImageView) findViewById(R.id.btnDelete);
    }

    private void setListner() {

        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);

        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnDelete.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

//        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        new Thread() {
//            @Override
//            public void run() {
//                vibrator.vibrate(500);
//            }
//        }.start();


        switch (v.getId()) {
            case R.id.btnNum0:
                onCharacterPressed('0');
                break;
            case R.id.btnNum1:
                onCharacterPressed('1');
                break;
            case R.id.btnNum2:
                onCharacterPressed('2');
                break;
            case R.id.btnNum3:
                onCharacterPressed('3');
                break;
            case R.id.btnNum4:
                onCharacterPressed('4');
                break;
            case R.id.btnNum5:
                onCharacterPressed('5');
                break;
            case R.id.btnNum6:
                onCharacterPressed('6');
                break;
            case R.id.btnNum7:
                onCharacterPressed('7');
                break;
            case R.id.btnNum8:
                onCharacterPressed('8');
                break;
            case R.id.btnNum9:
                onCharacterPressed('9');
                break;

            case R.id.btnDelete:
                onDeletePressed();
                break;
            case R.id.btnCall:
//                if (!checkPermission()) {
//                    requestPermission();
//                } else {
                    if (etPhoneNumber.getText().length() != 0) {
                        mbNumber = etPhoneNumber.getText().toString();
                        dialCall(etPhoneNumber.getText().toString());
                    }
//                }
                break;

            case R.id.etPhoneNumber:
                etPhoneNumber.setCursorVisible(true);
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {

        switch (view.getId()) {
            case R.id.btnDelete:
                etPhoneNumber.setText("");
                return true;
            default:
                break;
        }
        return false;
    }

    private void onCharacterPressed(char digit) {
        try {
            CharSequence cur = etPhoneNumber.getText();

            int start = etPhoneNumber.getSelectionStart();
            int end = etPhoneNumber.getSelectionEnd();
            int len = cur.length();
            if (cur.length() < 15) {

                if (cur.length() == 0) {
                    etPhoneNumber.setCursorVisible(false);
                }

                cur = cur.subSequence(0, start).toString() + digit + cur.subSequence(end, len).toString();
                etPhoneNumber.setText(cur);
                etPhoneNumber.setSelection(start + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void onDeletePressed() {
        CharSequence cur = etPhoneNumber.getText();
        int start = etPhoneNumber.getSelectionStart();
        int end = etPhoneNumber.getSelectionEnd();
        if (start == end) { // remove the item behind the cursor
            if (start != 0) {
                cur = cur.subSequence(0, start - 1).toString() + cur.subSequence(start, cur.length()).toString();
                etPhoneNumber.setText(cur);
                etPhoneNumber.setSelection(start - 1);
                if (cur.length() == 0) {
                    etPhoneNumber.setCursorVisible(false);
                }
            }
        } else { // remove the whole selection
            cur = cur.subSequence(0, start).toString() + cur.subSequence(end, cur.length()).toString();
            etPhoneNumber.setText(cur);
            etPhoneNumber.setSelection(end - (end - start));
            if (cur.length() == 0) {
                etPhoneNumber.setCursorVisible(false);
            }
        }
    }

    private void dialCall(final String mbNumber) {

        callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {

                if (state == TelephonyManager.CALL_STATE_RINGING) {

                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

                    try {
                        if (audioRecorder == null) {
                            audioRecorder = new AudioRecorder();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.MIC, RbDialerPadActivity.this);
                            else
                                audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.VOICE_CALL, RbDialerPadActivity.this);
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
                                pkAudioEntity = saveRecordingToDb(audioRecorder.getFilePath(), new LoginFacade(RbDialerPadActivity.this).getPanNumber(), mbNumber);
                                audioRecorder = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // service hit
                          new Dialer().validateMobile(new LoginFacade(RbDialerPadActivity.this).getUser().getEmpCode(), mbNumber, RbDialerPadActivity.this);   //  comment

                       // new Dialer().validateMobile("RB40000401", mbNumber, RbDialerPadActivity.this);

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
            audioEntity.setLead_id(0);
            // new AudioRecorderFacade(this).insertAudioRecord(audioEntity);    // comment
            return audioEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


   // private boolean checkPermission() {

//        int writeLogResult = ContextCompat.checkSelfPermission(getApplicationContext(), perms[0]);
//        int callPhomeResult = ContextCompat.checkSelfPermission(getApplicationContext(), perms[1]);
//        int readPhonestate = ContextCompat.checkSelfPermission(getApplicationContext(), perms[2]);
//        int recordAudio = ContextCompat.checkSelfPermission(getApplicationContext(), perms[3]);
//        int writeExternal = ContextCompat.checkSelfPermission(getApplicationContext(), perms[4]);
//        int fineLocation = ContextCompat.checkSelfPermission(getApplicationContext(), perms[5]);
//        int readContact = ContextCompat.checkSelfPermission(getApplicationContext(), perms[6]);
//
//        return writeLogResult == PackageManager.PERMISSION_GRANTED
//                && callPhomeResult == PackageManager.PERMISSION_GRANTED
//                && readPhonestate == PackageManager.PERMISSION_GRANTED
//                && recordAudio == PackageManager.PERMISSION_GRANTED
//                && writeExternal == PackageManager.PERMISSION_GRANTED
//                //&& coarseLocation == PackageManager.PERMISSION_GRANTED
//                && fineLocation == PackageManager.PERMISSION_GRANTED
//                && readContact == PackageManager.PERMISSION_GRANTED;
  //  }

//    private void requestPermission() {
//       // ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS);
//    }

// @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        Log.d("RbDialerPadActivity", "onRequestPermissionsResult");
//        switch (requestCode) {
//            case REQUEST_CODE_ASK_PERMISSIONS:
//                if (grantResults.length > 0) {
//
//                    boolean writeLog = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean callPhone = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    boolean phoneState = grantResults[2] == PackageManager.PERMISSION_GRANTED;
//                    boolean recordAudio = grantResults[3] == PackageManager.PERMISSION_GRANTED;
//                    boolean writeExternal = grantResults[4] == PackageManager.PERMISSION_GRANTED;
//
//                    boolean accessFine = grantResults[5] == PackageManager.PERMISSION_GRANTED;
//                    boolean readContact = grantResults[6] == PackageManager.PERMISSION_GRANTED;
//
//
//                    if (writeLog && callPhone && phoneState && recordAudio && writeExternal && accessFine && readContact) {
//
//                        if (etPhoneNumber.getText().length() != 0) {
//                            mbNumber = etPhoneNumber.getText().toString();
//                            dialCall(etPhoneNumber.getText().toString());
//                        }
//                    } else {
//
//                        //Permission Denied, You cannot access location data and camera
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                            showMessageOKCancel("You need to grant all permissions", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    finish();
//                                }
//                            });
//
//                        }
//
//                    }
//                }
//                break;
//        }
//    }
//

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(RbDialerPadActivity.this)
                .setTitle("Exit")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof ValidateMobileResponse) {
            if (response.getStatus_Id() == 0) {
                if (((ValidateMobileResponse) response).getResult().getLeadId() == 0) {
                    startActivity(new Intent(RbDialerPadActivity.this, RbAddLeadActivity.class)
                            .putExtra("DEMO", true)
                            .putExtra("PHONE_DIAL_NUMBER", mbNumber));
                } else {
                    //  startActivity(new Intent(RbDialerPadActivity.this, FeedBackActivity.class)
                    startActivity(new Intent(RbDialerPadActivity.this, FeedBackActivity.class)
                            .putExtra("PHONE_DIAL_NUMBER", mbNumber)
                            .putExtra("LEAD_ID", ((ValidateMobileResponse) response).getResult().getLeadId())
                            .putExtra("NAME", ((ValidateMobileResponse) response).getResult().getName()));
                }
            }
        }

        //////


    }

    @Override
    public void OnFailure(Throwable t) {
        Snackbar.make(etPhoneNumber, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
