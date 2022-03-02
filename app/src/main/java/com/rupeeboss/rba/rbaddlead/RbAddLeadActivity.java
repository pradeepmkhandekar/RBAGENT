package com.rupeeboss.rba.rbaddlead;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
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

import com.rupeeboss.rba.core.controller.leadcapture.LeadCapture;

import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AssigneeEntity;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.LeadRequest;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.model.StatusEntity;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.AudioRecordResponse;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RbAddLeadActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etName, etEmail, etMobile, etLoanAmt, etMonthlyIncome, etRemark, etDate, etTime, etExpctDisbsDate;
    Spinner spStatus, spProduct, spAssign;
    Button btnSubmit;
    ArrayList<String> arrayStatus, arrayProduct, arrayAssignee;
    ArrayAdapter<String> statusAdapter;
    ArrayAdapter<String> productAdapter;
    ArrayAdapter<String> assigneeAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    AudioEntity audioEntity;
    String mobileNumber;
    boolean demo;
    RadioGroup radioGroup;
    RadioButton radioButton;
    HomeLoanRequest homeLoanRequest;
    PersonalLoanRequest personalLoanRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rb_add_lead);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrayStatus = new ArrayList<String>();
        arrayProduct = new ArrayList<String>();
        arrayAssignee = new ArrayList<String>();
        initialize();
        loadSpinner();

        Calendar calendar = Calendar.getInstance();

        etDate.setText(simpleDateFormat.format(calendar.getTime()));
        etTime.setText(simpleTimeFormat.format(calendar.getTime()));
        etExpctDisbsDate.setText(simpleDateFormat.format(calendar.getTime()));
        mobileNumber = getIntent().getStringExtra("PHONE_DIAL_NUMBER");
        demo = getIntent().getBooleanExtra("DEMO", false);
        if (demo) {
            radioGroup.setVisibility(View.VISIBLE);
        }
        etMobile.setText(mobileNumber);


        if (getIntent().hasExtra(Constants.HL_REQUEST)) {
            homeLoanRequest = getIntent().getParcelableExtra(Constants.HL_REQUEST);
            etName.setText(homeLoanRequest.getApplicantNme());
            etLoanAmt.setText(homeLoanRequest.getPropertyCost());
            // etExpctDisbsDate.setVisibility(View.GONE);
            etMonthlyIncome.setText(homeLoanRequest.getApplicantIncome());
            String prodName = new LoginFacade(RbAddLeadActivity.this).getProductName(Integer.parseInt(homeLoanRequest.getProductId()));
            if (arrayProduct.indexOf(prodName) != -1) {
                spProduct.setSelection(arrayProduct.indexOf(prodName));
                spProduct.setEnabled(false);
            }
        } else if (getIntent().hasExtra(Constants.PL_REQUEST)) {
            personalLoanRequest = getIntent().getParcelableExtra(Constants.PL_REQUEST);
            etName.setText(personalLoanRequest.getApplicantNme());
            etLoanAmt.setText(personalLoanRequest.getLoanRequired());
            // etExpctDisbsDate.setVisibility(View.GONE);
            etMonthlyIncome.setText(personalLoanRequest.getApplicantIncome());
            String prodName = new LoginFacade(RbAddLeadActivity.this).getProductName(9);
            if (arrayProduct.indexOf(prodName) != -1) {
                spProduct.setSelection(arrayProduct.indexOf(prodName));
                spProduct.setEnabled(false);
            }
        } else if (getIntent().hasExtra(Constants.LAP_REQUEST)) {
            homeLoanRequest = getIntent().getParcelableExtra(Constants.LAP_REQUEST);
            etName.setText(homeLoanRequest.getApplicantNme());
            etLoanAmt.setText(homeLoanRequest.getPropertyCost());
            // etExpctDisbsDate.setVisibility(View.GONE);
            etMonthlyIncome.setText(homeLoanRequest.getApplicantIncome());
            String prodName = new LoginFacade(RbAddLeadActivity.this).getProductName(Integer.parseInt(homeLoanRequest.getProductId()));
            if (arrayProduct.indexOf(prodName) != -1) {
                spProduct.setSelection(arrayProduct.indexOf(prodName));
                spProduct.setEnabled(false);
            }
        }
    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, RbAddLeadActivity.this);
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
            Utility.hideKeyBoard(view, RbAddLeadActivity.this);
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    etTime.setText(selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    };

    private void initialize() {
        spAssign = (Spinner) findViewById(R.id.sp_lead_assign);
        etName = (EditText) findViewById(R.id.et_lead_name);
        etEmail = (EditText) findViewById(R.id.et_lead_email);
        etMobile = (EditText) findViewById(R.id.et_lead_mobile);
        etRemark = (EditText) findViewById(R.id.etRemark);
        etLoanAmt = (EditText) findViewById(R.id.et_lead_loan_amount);
        etMonthlyIncome = (EditText) findViewById(R.id.et_lead_monthly_income);
        etDate = (EditText) findViewById(R.id.et_lead_date);
        etTime = (EditText) findViewById(R.id.et_lead_time);
        spStatus = (Spinner) findViewById(R.id.sp_lead_status);

        spProduct = (Spinner) findViewById(R.id.sp_lead_product);
        btnSubmit = (Button) findViewById(R.id.btn_lead_submit);
        btnSubmit.setOnClickListener(this);
        etDate.setOnClickListener(datePickerDialog);
        etTime.setOnClickListener(timePickerDialog);
        radioGroup = (RadioGroup) findViewById(R.id.rg_demostatus);
        etExpctDisbsDate = (EditText) findViewById(R.id.etExpctDisbsDate);
        etExpctDisbsDate.setOnClickListener(nextDatePickerDialog);

    }

    private void loadSpinner() {
        statusAdapter = new ArrayAdapter<String>(RbAddLeadActivity.this,
                android.R.layout.simple_spinner_item, getStatusList());
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);

        assigneeAdapter = new ArrayAdapter<String>(RbAddLeadActivity.this,
                android.R.layout.simple_spinner_item, getAssigneeList());
        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAssign.setAdapter(assigneeAdapter);

        productAdapter = new ArrayAdapter<String>(RbAddLeadActivity.this,
                android.R.layout.simple_spinner_item, getProductList());
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(productAdapter);
    }

    protected View.OnClickListener nextDatePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, RbAddLeadActivity.this);
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

    private ArrayList<String> getProductList() {
        List<ProductsEntity> productEntities = new LoginFacade(RbAddLeadActivity.this).getProductList();
        for (ProductsEntity entity : productEntities) {
            arrayProduct.add(entity.getProdName());
        }

        return arrayProduct;
    }

    private ArrayList<String> getStatusList() {

        List<StatusEntity> statusEntityList = new LoginFacade(RbAddLeadActivity.this).getStatusList();
        for (StatusEntity entity : statusEntityList) {
            arrayStatus.add(entity.getStatusName());
        }

        return arrayStatus;
    }

    private ArrayList<String> getAssigneeList() {
        List<AssigneeEntity> assigneeEntities = new LoginFacade(RbAddLeadActivity.this).getAssigneeList();
        for (AssigneeEntity entity : assigneeEntities) {
            arrayAssignee.add(entity.getAssigneeName());
        }

        return arrayAssignee;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_lead_submit) {
            Utility.hideKeyBoard(view, RbAddLeadActivity.this);

            if (etName.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Name", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (etMobile.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Mobile", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (spAssign.getSelectedItem() != null
                    && spProduct.getSelectedItem() != null
                    && spAssign.getSelectedItem() != null) {

                int demoGiven = 0;
                if (demo) {

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    if (radioButton.getText().toString().matches("Demo Given")) {
                        demoGiven = 1;
                    }
                }


                LeadRequest leadRequest = new LeadRequest();
                leadRequest.setName(etName.getText().toString());
                leadRequest.setMobile(etMobile.getText().toString());
                leadRequest.setCity("");
                leadRequest.setCompany("");
                leadRequest.setDesignation("");
                leadRequest.setProfession("");


                leadRequest.setAssgnId("" + new LoginFacade(RbAddLeadActivity.this).getAssigneeId((spAssign.getSelectedItem().toString())));
                leadRequest.setFollowupDate(etDate.getText().toString());
                leadRequest.setEMail(etEmail.getText().toString());
                leadRequest.setLoan_amnt(etLoanAmt.getText().toString());
                leadRequest.setMonthly_income(etMonthlyIncome.getText().toString());
                leadRequest.setProduct(spProduct.getSelectedItem().toString());
                leadRequest.setStatus(new LoginFacade(RbAddLeadActivity.this).getStatusId((spStatus.getSelectedItem().toString())));
                leadRequest.setEmpCode(String.valueOf(new LoginFacade(RbAddLeadActivity.this).getPanNumber()));
                leadRequest.setRemark(etRemark.getText().toString());
                leadRequest.setBrokerId(new LoginFacade(RbAddLeadActivity.this).getUser().getBrokerId());
                leadRequest.setDemoGiven(demoGiven);
                leadRequest.setExpctDisbsDate(etExpctDisbsDate.getText().toString());
                showProgressDialog();
                new LeadCapture().insertLead(leadRequest, this);
            } else {
                Snackbar.make(etName, "You are not authorize person, please contact your administrator.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void OnSuccess(APIResponse response, String message) {
        dismissDialog();
        if (response instanceof LeadResponse) {
            if (response.getStatus_Id() == 0) {
                etEmail.setText("");
                etMobile.setText("");
                etLoanAmt.setText("");
                etMonthlyIncome.setText("");
                etRemark.setText("");
                etName.setText("");
                if (homeLoanRequest != null || personalLoanRequest != null) {
                    finish();
                }
            }
            Snackbar.make(etRemark, response.getMessage(), Snackbar.LENGTH_SHORT).show();
        } else if (response instanceof AudioRecordResponse) {

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(etRemark, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }


}
