package com.rupeeboss.rba.rbfeedback;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.audio.AudioController;
import com.rupeeboss.rba.core.controller.feedback.FeedBack;
import com.rupeeboss.rba.core.database.AudioRecorderFacade;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.managecalllog.ManageCallLog;
import com.rupeeboss.rba.core.model.AssigneeEntity;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.model.StatusEntity;
import com.rupeeboss.rba.core.response.AudioRecordResponse;
import com.rupeeboss.rba.core.response.FeedbackResponse;
import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etName, etDate, etTime, etRemark, etExpctDisbsDate;
    Spinner spStatus, spProduct, spAssign;
    Button btnSubmit;
    String empCode;
    ArrayList<String> arrayStatus, arrayProduct, arrayAssignee;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    Date now = Calendar.getInstance().getTime();
    long timePortion = now.getTime() % MILLIS_PER_DAY;
    String mobileNumber;
    int leadId = 0;
    ArrayAdapter<String> statusAdapter;
    ArrayAdapter<String> assigneeAdapter;
    ArrayAdapter<String> productAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AudioEntity audioEntity;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences = getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        empCode = sharedPreferences.getString(Utility.EMPLOYEE_ID, "");
        editor.putString(Utility.IS_FEEDBACK, "NO");
        editor.commit();
        arrayStatus = new ArrayList<String>();
        arrayProduct = new ArrayList<String>();
        arrayAssignee = new ArrayList<String>();

        loadSpinner();

        Calendar calendar = Calendar.getInstance();

        etDate.setText(simpleDateFormat.format(calendar.getTime()));
        etTime.setText(simpleTimeFormat.format(calendar.getTime()));
        etExpctDisbsDate.setText(simpleDateFormat.format(calendar.getTime()));
        //etName.setText(Utility.EmpName);

        if (getIntent().getStringExtra("FOLLOW_PHONE_NUMBER") != null) {
            mobileNumber = getIntent().getStringExtra("FOLLOW_PHONE_NUMBER");
            leadId = getIntent().getIntExtra("LEAD_ID", 0);
            etName.setText(getIntent().getStringExtra("NAME_FOLLOW_FEEDBACK"));

        } else if (getIntent().getStringExtra("PHONE_NUMBER") != null) {
            mobileNumber = getIntent().getStringExtra("PHONE_NUMBER");
            leadId = getIntent().getIntExtra("LEAD_ID", 0);
            etName.setText(getIntent().getStringExtra("NAME_FOLLOW"));

        } else if (getIntent().getStringExtra("PHONE_DIAL_NUMBER") != null) {
            mobileNumber = getIntent().getStringExtra("PHONE_DIAL_NUMBER");
            leadId = getIntent().getIntExtra("LEAD_ID", 0);
            etName.setText(getIntent().getStringExtra("NAME"));

        }

        if (getIntent().getStringExtra("FOLLOW_PHONE_NUMBER") == null) {
            if (mobileNumber != "" || mobileNumber != null) {
                ManageCallLog manageCallLog = new ManageCallLog();
                manageCallLog.deleteNumberFromCallLog(FeedBackActivity.this, mobileNumber);
            }
            //uploadGPSLog();
            uploadRecording();
        }
    }


    private void uploadRecording() {
        //upload audio to server
        try {
            List<AudioEntity> audioEntities = new AudioRecorderFacade(FeedBackActivity.this).getAllAudioRecord();
            if (Utility.checkInternetStatus(FeedBackActivity.this)) {
                for (AudioEntity audioEntity : audioEntities) {
                    this.audioEntity = audioEntity;
                    new AudioController(this).uploadAudioRecord(this.audioEntity, this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSpinner() {

        statusAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getStatusList());
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);

        assigneeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getAssigneeList());
        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAssign.setAdapter(assigneeAdapter);

        productAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getProductList());
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(productAdapter);
    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, FeedBackActivity.this);
            DateTimePicker.showDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etDate.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };

    protected View.OnClickListener timePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, FeedBackActivity.this);
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    //etTime.setText(selectedHour + ":" + selectedMinute);
                    etTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                }
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    };
    protected View.OnClickListener nextDatePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, FeedBackActivity.this);
            DateTimePicker.showNextDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etExpctDisbsDate.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };


    private void initialize() {
        etName = (EditText) findViewById(R.id.et_name);
        etDate = (EditText) findViewById(R.id.et_date);
        etTime = (EditText) findViewById(R.id.et_time);
        etRemark = (EditText) findViewById(R.id.etRemark);
        etExpctDisbsDate = (EditText) findViewById(R.id.etExpctDisbsDate);
        spStatus = (Spinner) findViewById(R.id.sp_status);
        spAssign = (Spinner) findViewById(R.id.sp_assign);
        spProduct = (Spinner) findViewById(R.id.sp_product);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etDate.setOnClickListener(datePickerDialog);
        etTime.setOnClickListener(timePickerDialog);
        etExpctDisbsDate.setOnClickListener(nextDatePickerDialog);
        radioGroup = (RadioGroup) findViewById(R.id.rg_demostatus);
    }

    private void deleteDialedLog(String calledNumber) {
        ManageCallLog manageCallLog = new ManageCallLog();
        if (calledNumber == CallLog.Calls.getLastOutgoingCall(FeedBackActivity.this))
            manageCallLog.deleteNumberFromCallLog(getApplicationContext(), CallLog.Calls.getLastOutgoingCall(FeedBackActivity.this));

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_submit) {
            Utility.hideKeyBoard(view, FeedBackActivity.this);
            if (etRemark.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Remark", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (spAssign.getSelectedItem() != null
                    && spProduct.getSelectedItem() != null
                    && spAssign.getSelectedItem() != null) {
                int demo = 0;
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                if (radioButton.getText().toString().matches("Demo Given")) {
                    demo = 1;
                }
                int statusID = new LoginFacade(FeedBackActivity.this).getStatusId(spStatus.getSelectedItem().toString());
                int ProductID = new LoginFacade(FeedBackActivity.this).getProductId(spProduct.getSelectedItem().toString());
                int assigneeID = new LoginFacade(FeedBackActivity.this).getAssigneeId(spAssign.getSelectedItem().toString());

                String remark = etRemark.getText().toString();
                String selectedDate = etDate.getText().toString();
                String selectedTime = etTime.getText().toString();
                String expectDisb = etExpctDisbsDate.getText().toString();
                showProgressDialog();
                new FeedBack().sendFeedback(String.valueOf(empCode), leadId, Utility.EmpName, statusID,
                        remark, assigneeID, ProductID, selectedDate, selectedTime, demo,expectDisb, this);
            } else {
                Snackbar.make(etName, "You are not authorize person, please contact your administrator.", Snackbar.LENGTH_SHORT).show();
            }

        }
    }


    private ArrayList<String> getProductList() {
        List<ProductsEntity> productEntities = new LoginFacade(FeedBackActivity.this).getProductList();
        for (ProductsEntity entity : productEntities) {
            arrayProduct.add(entity.getProdName());
        }

        return arrayProduct;
    }

    private ArrayList<String> getStatusList() {

        List<StatusEntity> statusEntityList = new LoginFacade(FeedBackActivity.this).getStatusList();
        for (StatusEntity entity : statusEntityList) {
            arrayStatus.add(entity.getStatusName());
        }

        return arrayStatus;
    }

    private ArrayList<String> getAssigneeList() {
        List<AssigneeEntity> assigneeEntities = new LoginFacade(FeedBackActivity.this).getAssigneeList();
        for (AssigneeEntity entity : assigneeEntities) {
            arrayAssignee.add(entity.getAssigneeName());
        }

        return arrayAssignee;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        dismissDialog();
        if (response instanceof FeedbackResponse) {
            if (response.getStatus_Id() == 0) {

                if (((FeedbackResponse) response).getResult() != null) {
                    etName.setText(((FeedbackResponse) response).getResult().getName());
                } else {
                    finish();
                }
            } else {
                Snackbar.make(etRemark, response.getMsg(), Snackbar.LENGTH_SHORT).show();
            }
        } else if (response instanceof AudioRecordResponse) {
            if (response.getStatus_Id() == 0) {
                try {

                    if (((AudioRecordResponse) response).getLocaldb_id().matches("" + audioEntity.getId())) {
                        boolean isDbrecordDeleted = new AudioRecorderFacade(this).deleteAudioRecord(audioEntity);
                        boolean isDeleted = Utility.deleteAudioFile(audioEntity.getFile_name());

                        Log.d("AUDIO_DELETED", "" + isDeleted);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //

    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etRemark, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

}
